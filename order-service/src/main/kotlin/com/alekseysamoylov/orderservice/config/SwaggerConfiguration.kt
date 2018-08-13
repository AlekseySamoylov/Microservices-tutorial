package com.alekseysamoylov.orderservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alekseysamoylov.orderservice.controllers"))
                .paths(PathSelectors.any())
                .build().apiInfo(ApiInfo(
                        "Order Service Documentation",
                        "Documentation automatically generated", "v1.1", null,
                        Contact("Aleksey Samoylov", "alekseysamoylov.com",
                                "alekseysamoylov89@gmail.com"), null, null))
    }
}
