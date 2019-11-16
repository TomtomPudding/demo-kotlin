package com.tom.demokotlin.controller.TopPage

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("samples")
class TopPageController {
    @GetMapping
    fun top():String{
        return "sample"
    }
}