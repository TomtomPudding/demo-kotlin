package com.tom.demokotlin.login.handler

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(request: HttpServletRequest, response: HttpServletResponse, accessDeniedException: AccessDeniedException) {
        // HTTPステータスのみ設定する
        response.setStatus(HttpServletResponse.SC_FORBIDDEN)
    }
}