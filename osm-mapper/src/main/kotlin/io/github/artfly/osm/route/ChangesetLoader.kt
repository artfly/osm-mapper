package io.github.artfly.osm.route

import io.github.artfly.osm.generated.ChangeCollection
import io.github.artfly.osm.generated.Changeset
import io.github.artfly.osm.model.Node
import io.github.artfly.osm.model.NodeUpdate
import io.github.artfly.osm.model.Point
import io.github.artfly.osm.model.UpdateStatus
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.stax.StAXBuilder.stax
import org.apache.camel.model.dataformat.JaxbDataFormat
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class ChangesetLoader : RouteBuilder() {
    override fun configure() {

        interceptSendToEndpoint("direct:saveOrUpdateNode").skipSendToOriginalEndpoint()
                .`when`(body().isNull)
        interceptSendToEndpoint("direct:deleteNode").skipSendToOriginalEndpoint()
                .`when`(body().isNull)

        from("timer:changeset-load?delay={{osm.delay:0s}}&period={{osm.period:10m}}&fixedRate=true")
                .setHeader("maxLon", method("nodeRepository", "findMaxLon"))
                .setHeader("minLon", method("nodeRepository", "findMinLon"))
                .setHeader("maxLat", method("nodeRepository", "findMaxLat"))
                .setHeader("minLat", method("nodeRepository", "findMinLat"))
                .setHeader("updatedFrom", method("updateRepository", "findLastUpdatedAt"))
                .setHeader("updatedTo").spel("#{request.headers['updatedFrom'].plusSeconds(@environment.getProperty('osm.changesetsPeriod', T(java.lang.Long), 24 * 60 * 60), )}")
                .process { exchange ->
                    exchange.`in`.setHeader("nodeUpdate", NodeUpdate(updatedFrom = exchange.`in`.getHeader("updatedFrom") as Instant,
                            updatedTo = exchange.`in`.getHeader("updatedTo") as Instant,
                            status = UpdateStatus.STARTED))
                }
                .setHeader("nodeUpdate", method("updateRepository", "save(\${header.nodeUpdate})"))
                .to("direct:loadChangesets")
                .onCompletion()
                .process { exchange -> (exchange.`in`.getHeader("nodeUpdate") as NodeUpdate).status = UpdateStatus.FINISHED }
                .bean("updateRepository", "save(\${header.nodeUpdate})")


        from("direct:loadChangesets")
                .log("http4://api.openstreetmap.org/api/0.6/changesets?bbox=\${header.minLon},\${header.minLat},\${header.maxLon},\${header.maxLat}&time=\${header.updatedFrom},\${header.updatedTo}")
                .recipientList(simple("http4://api.openstreetmap.org/api/0.6/changesets?bbox=\${header.minLon},\${header.minLat},\${header.maxLon},\${header.maxLat}&time=\${header.updatedFrom},\${header.updatedTo}"), "false")
                .split(stax(Changeset::class.java))
                .streaming()
                .setHeader("id", simple("\${body.id}"))
                .setBody(constant(null))
                .recipientList(simple("http4://api.openstreetmap.org/api/0.6/changeset/\${header.id}/download"))
                .to("direct:applyChangeset")

        from("direct:applyChangeset")
                .unmarshal(JaxbDataFormat().apply { this.contextPath = ChangeCollection::class.java.`package`.name })
                .multicast()
                .to("direct:removeNodes", "direct:modifyNodes", "direct:createNodes")

        from("direct:removeNodes")
                .setBody(simple("\${body?.delete}"))
                .split(body()).streaming()
                .setBody(simple("\${body?.node?.id?.longValue}"))
                .to("direct:deleteNode")

        from("direct:modifyNodes")
                .setBody(simple("\${body?.modify}"))
                .split(body()).streaming()
                .setBody(simple("\${body?.node}"))
                .log("\${body}")
                .to("direct:saveOrUpdateNode")

        from("direct:createNodes")
                .setBody(simple("\${body?.create}"))
                .split(body()).streaming()
                .setBody(simple("\${body?.node}"))
                .to("direct:saveOrUpdateNode")

        from("direct:deleteNode")
                .process { exchange -> exchange.out.body = Node(exchange.`in`.getBody(Long::class.java)) }
                .log("\${body}")
                .bean("nodeRepository", "delete(\${body})")

        from("direct:saveOrUpdateNode")
                .log("\${body}")
                .process { exchange ->
                    val node = exchange.`in`.getBody(io.github.artfly.osm.generated.Node::class.java)
                    if (node != null) {
                        exchange.out.body = Node(node.id.toLong(), Point(node.lat, node.lon),
                                node.tag.associateBy({ it.k }, { it.v }))
                    }
                }
                .bean("nodeRepository", "save(\${body})")

        from("direct:test")
                .to("stream:out")

    }

}