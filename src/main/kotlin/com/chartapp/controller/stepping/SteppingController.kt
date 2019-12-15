package com.chartapp.controller.stepping

import com.chartapp.session.SessionData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("stepping")
class SteppingController {
    @Autowired
    protected lateinit var session: SessionData

    @GetMapping
    fun getSuccess():String{
        var nextpage:String? = session.nextpage
        return "redirect:" + nextpage;
    }

    @PostMapping
    fun postSuccess():String{
        var nextpage:String? = session.nextpage
        return "redirect:" + nextpage;
    }
}