package com.aej.ojekkuapi.location.services

import com.aej.ojekkuapi.location.entity.Coordinate
import com.aej.ojekkuapi.location.entity.Location
import com.aej.ojekkuapi.location.entity.Routes

interface LocationServices {
    fun searchLocation(name: String, coordinate: Coordinate): Result<List<Location>>
    fun reverseLocation(coordinate: Coordinate): Result<Location>
    fun getRoutes(origin: Coordinate, destination: Coordinate): Result<Routes>
}