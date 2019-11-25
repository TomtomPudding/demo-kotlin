package com.tom.demokotlin.login.config


import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * ログイン画面のコントローラ部分の役割はWebSecurityConfigが担っているので、
 * ViewNameと画面の対応づけを行うために実装
 * @author tom
 */
@Configuration
class LoginConfig : WebMvcConfigurer {

    /**
     * 「/login」というURLからlogin.htmlを呼び出す
     */
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/login").setViewName("login")
    }

}