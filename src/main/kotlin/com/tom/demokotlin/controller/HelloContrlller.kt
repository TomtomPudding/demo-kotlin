package com.tom.demokotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloContrlller {
    @GetMapping("hello")
    fun hello(@RequestParam("name") name: String): String = "Hello, $name"
}