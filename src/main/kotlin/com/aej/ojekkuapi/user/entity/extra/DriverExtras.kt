package com.aej.ojekkuapi.user.entity.extra

import com.fasterxml.jackson.annotation.JsonProperty

class DriverExtras(
    @JsonProperty("vehicles_number")
    val vehiclesNumber: String = ""
) : Extras()