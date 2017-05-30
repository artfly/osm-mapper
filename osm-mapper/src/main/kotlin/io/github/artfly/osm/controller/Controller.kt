package io.github.artfly.osm.controller

import io.github.artfly.osm.model.NodeRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class NodeController (val repository: NodeRepository) {
    @GetMapping("/nodes")
    fun findNodes(@RequestParam("lat") lat: Double,
                  @RequestParam("lon") lon: Double,
                  @RequestParam("radius") radius: Double) {
        repository.findInRadius(radius, lon, lat)
    }
}