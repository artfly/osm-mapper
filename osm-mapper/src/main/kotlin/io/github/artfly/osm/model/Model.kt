package io.github.artfly.osm.model

import org.postgresql.util.HStoreConverter
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.AttributeConverter
import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Converter
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(
        indexes = arrayOf(
                Index(name = "idx_node_lon", columnList = "x"),
                Index(name = "idx_node_lat", columnList = "y")
        )
)
data class Node(
        @Id
        var id: Long? = null,
        @Embedded
        @AttributeOverrides(
                AttributeOverride(name = "lon", column = Column(name = "x")),
                AttributeOverride(name = "lat", column = Column(name = "y"))
        )
        var point: Point = Point(),
        @Convert(converter = MapToHStoreConverter::class)
        @Column(columnDefinition = "hstore")
        var tags: Map<String, String> = mapOf()
)

@Embeddable
data class Point(
        var x: Double? = null,
        var y: Double? = null
)

@Converter
class MapToHStoreConverter : AttributeConverter<Map<String, String>, String> {
    override fun convertToDatabaseColumn(attribute: Map<String, String>?): String? = HStoreConverter.toString(attribute)

    override fun convertToEntityAttribute(dbData: String?): Map<String, String> = HStoreConverter.fromString(dbData)
}

@Entity
data class NodeUpdate(
        @Id
        @SequenceGenerator(name = "seq_node_update_id", sequenceName = "seq_node_update_id")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_node_update_id")
        var id: Long? = null,
        @Convert(converter = InstantToTimestampConverter::class)
        @Column(columnDefinition = "timestamp", unique = true)
        var updatedFrom: Instant = Instant.now(),
        @Convert(converter = InstantToTimestampConverter::class)
        @Column(columnDefinition = "timestamp")
        var updatedTo: Instant = Instant.now(),
        @Enumerated(EnumType.STRING)
        var status: UpdateStatus = UpdateStatus.STARTED
)

enum class UpdateStatus {STARTED, FINISHED}

class InstantToTimestampConverter : AttributeConverter<Instant, Timestamp> {
    override fun convertToDatabaseColumn(instant: Instant?): Timestamp  = Timestamp.from(instant)

    override fun convertToEntityAttribute(timestamp: Timestamp?): Instant = timestamp?.toInstant() ?: Instant.now()
}