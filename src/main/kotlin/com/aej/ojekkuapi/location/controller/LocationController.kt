package com.aej.ojekkuapi.location.controller

import com.aej.ojekkuapi.location.entity.Location
import com.aej.ojekkuapi.location.entity.Routes
import com.aej.ojekkuapi.location.services.LocationServices
import com.aej.ojekkuapi.utils.BaseResponse
import com.aej.ojekkuapi.utils.coordinateStringToData
import com.aej.ojekkuapi.utils.toResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/location")
class LocationController {

    @Autowired
    private lateinit var locationServices: LocationServices

    @GetMapping("/search")
    fun searchLocation(
        @RequestParam name: String,
        @RequestParam coordinate: String
    ): BaseResponse<List<Location>> {
        val coordinateData = coordinate.coordinateStringToData()
        return locationServices.searchLocation(name, coordinateData).toResponse()
    }

    @GetMapping("/reverse")
    fun reverseLocation(
        @RequestParam(value = "coordinate", required = true) coordinate: String
    ): BaseResponse<Location> {
        val coordinateData = coordinate.coordinateStringToData()
        return locationServices.reverseLocation(coordinateData).toResponse()
    }

    @GetMapping("/routes")
    fun routesLocation(
        @RequestParam(value = "origin") origin: String,
        @RequestParam(value = "destination") destination: String
    ): BaseResponse<Routes> {
        val coordinateOrigin = origin.coordinateStringToData()
        val coordinateDestination = destination.coordinateStringToData()
        return locationServices.getRoutes(coordinateOrigin, coordinateDestination).toResponse()
    }
}