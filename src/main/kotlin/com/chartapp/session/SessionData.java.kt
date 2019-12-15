package com.chartapp.session

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
class SessionData  :Serializable{
    internal var nextpage: String? = null

}