package com.aej.ojekkuapi.utils.extensions

import com.fasterxml.jackson.databind.ObjectMapper

fun <T : Any, U : Any> T.safeClassTo(clazz: Class<U>): U {
    val json = ObjectMapper().writeValueAsString(this)
    return ObjectMapper().readValue(json, clazz)
}