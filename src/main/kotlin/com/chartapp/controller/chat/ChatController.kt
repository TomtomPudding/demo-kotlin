package com.chartapp.controller.chat

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("chat")
class ChatController {
    @GetMapping
    fun openChatPage():String{
        return "talk"
    }
}