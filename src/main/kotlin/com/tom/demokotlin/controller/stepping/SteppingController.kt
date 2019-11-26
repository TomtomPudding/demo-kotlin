package com.tom.demokotlin.controller.stepping

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("stepping")
class SteppingController {

    @PostMapping
    fun success():String{
        return "redirect:chat";
    }
}