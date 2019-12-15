package com.chartapp.login.controller

import com.chartapp.login.entity.LoginPageEntity
import com.chartapp.session.SessionData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("login")
class LoginController {
    @Autowired
    protected lateinit var session: SessionData

    @GetMapping
    fun openLoginPage(@ModelAttribute page: LoginPageEntity):String{
        var nextpage = if(page.nextPage.isEmpty()) "chat" else page.nextPage

        session.nextpage = nextpage
        return "login"
    }
}