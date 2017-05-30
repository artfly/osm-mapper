package io.github.artfly.osm.converter

import io.github.artfly.osm.generated.Node
import org.springframework.stereotype.Component
import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller
import javax.xml.stream.XMLEventReader

@Component
class NodeConverter {
    val unmarshaller: Unmarshaller = JAXBContext.newInstance(Node::class.java).createUnmarshaller()

    fun readNode(reader: XMLEventReader): Node = unmarshaller.unmarshal(reader) as Node

    fun hasNextNode(reader: XMLEventReader): Boolean {
        while (reader.hasNext()) {
            val event = reader.peek()
            if (event.isStartElement && event.asStartElement().name.localPart == "node") {
                return true
            }
            reader.nextEvent()
        }
        return false
    }
}