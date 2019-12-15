package com.chartapp.login.handler
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        // HTTPステータスのみ設定する
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
    }
}
