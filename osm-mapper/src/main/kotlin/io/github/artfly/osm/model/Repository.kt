package io.github.artfly.osm.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.Instant

/**
 * Dao for entities.
 *
 * @author asartakov
 */

@Repository
interface NodeRepository : JpaRepository<Node, Long> {

    @Query("select * from node where point ( :longitude, :latitude ) <@> point (x, y) < :radius", nativeQuery = true)
    fun findInRadius(@Param("radius") radius: Double, @Param("longitude") longitude: Double,
                     @Param("latitude") latitude: Double): List<Node>

    @Query("select n.point.x from Node n where point.x = (select max(point.x) from Node)")
    fun findMaxLon(): Double

    @Query("select n.point.x from Node n where point.x = (select min(point.x) from Node)")
    fun findMinLon(): Double

    @Query("select n.point.y from Node n where point.y = (select max(point.y) from Node)")
    fun findMaxLat(): Double

    @Query("select n.point.y from Node n where point.y = (select min(point.y) from Node)")
    fun findMinLat(): Double
}

@Repository
interface UpdateRepository : JpaRepository<NodeUpdate, Long> {

//    @Query("select coalesce ( " +
//            " (select max(nu.updated_at) from node_update nu where nu.status='FINISHED'), " +
//            " '1970-01-01' " +
//            ")",
//            nativeQuery = true)
    @Query("select coalesce(max(nu.updatedTo), '1970-01-01') from NodeUpdate nu where nu.status='FINISHED'")
    fun findLastUpdatedAt(): Instant
}