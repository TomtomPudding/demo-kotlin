package com.tom.demokotlin.swagger.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    fun demoApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false) // defaultのResponse Code/Messageを表示しない
                .select()
                .paths(PathSelectors.regex("/hello*")) // /hello配下を明示的に選択する
                .build()
                .apiInfo(apiInfo())
    }

    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Chart App API!!")
                .description("This is an API document powered by swagger.")
                .version("0.0.0")
                .build()
    }
}