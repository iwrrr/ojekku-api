package com.aej.ojekkuapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class SimpleController {

    @GetMapping("/ping")
    fun ping(): String {
        return "ok"
    }
}