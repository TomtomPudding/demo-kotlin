package com.chartapp.login.handler

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 認証成功時
 */
@Component
class SuccessAccessHandler : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse,
                                         authentication: Authentication) {
        response.sendRedirect(request.getContextPath() + "/stepping");
    }

}