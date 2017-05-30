package io.github.artfly.osm

import io.github.artfly.osm.converter.NodeConverter
import io.github.artfly.osm.model.NodeRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@SpringBootApplication
open class OsmMapperApplication {

    @Bean
    fun init(converter: NodeConverter, repository: NodeRepository, env: Environment) = CommandLineRunner {
        //        val reader = XMLInputFactory.newFactory()
//                .createXMLEventReader(FileInputStream(env.getProperty("osm.file")))
//
//        val batchSize = env.getProperty("osm.batchSize").toInt()
//        val nodes = mutableListOf<Node>()
//
//        while (converter.hasNextNode(reader)) {
//            val node = converter.readNode(reader)
//            nodes.add(Node(node.id.toLong(), Point(node.lat, node.lon), node.tag.associateBy({it.k}, {it.v})))
//            if (nodes.size == batchSize) {
//                repository.save(nodes)
//                nodes.clear()
//            }
//        }
//        repository.save(nodes)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(OsmMapperApplication::class.java, *args)
}