package com.tom.demokotlin.controller.topPage

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("samples")
class TopPageController {
    @GetMapping
    fun openTopPage():String{
        return "sample"
    }
}