package com.chartapp.rest.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("hello")
    fun hello(hello: HelloEntity): String {
        return hello.word
    }
}