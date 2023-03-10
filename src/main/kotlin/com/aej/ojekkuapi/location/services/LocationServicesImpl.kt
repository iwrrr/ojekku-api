package com.aej.ojekkuapi.location.services

import com.aej.ojekkuapi.location.component.LocationComponent
import com.aej.ojekkuapi.location.mapper.Mapper
import com.aej.ojekkuapi.location.entity.Coordinate
import com.aej.ojekkuapi.location.entity.Location
import com.aej.ojekkuapi.location.entity.Routes
import com.aej.ojekkuapi.utils.extensions.orThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationServicesImpl(
    @Autowired
    private val fetcher: LocationComponent
) : LocationServices {

    override fun searchLocation(name: String, coordinate: Coordinate): Result<List<Location>> {
        return fetcher.searchLocation(name, coordinate).map {
            Mapper.mapSearchLocationHereToLocation(it)
        }
    }

    override fun reverseLocation(coordinate: Coordinate): Result<Location> {
        return fetcher.reverseLocation(coordinate).map {
            Mapper.mapSearchLocationHereToLocation(it).firstOrNull().orThrow("Location not found")
        }
    }

    override fun getRoutes(origin: Coordinate, destination: Coordinate): Result<Routes> {
        return fetcher.getRoutes(origin, destination).map {
            val coordinates = Mapper.mapRoutesHereToRoutes(it)
            Routes(coordinates)
        }
    }
}