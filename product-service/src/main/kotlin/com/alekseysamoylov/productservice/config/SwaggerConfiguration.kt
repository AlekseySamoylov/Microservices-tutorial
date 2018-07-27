package com.alekseysamoylov.productservice.config

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
    fun api11(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("product-api-1.1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alekseysamoylov.productservice.controllers"))
                .paths(PathSelectors.regex("/product/v1.1.*"))
                .build().apiInfo(ApiInfo(
                        "Product Service Documentation",
                        "Documentation automatically generated", "v1.1", null,
                        Contact("Aleksey Samoylov", "alekseysamoylov.com",
                                "alekseysamoylov89@gmail.com"), null, null))
    }

    @Bean
    fun api10(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("product-api-1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alekseysamoylov.productservice.controllers"))
                .paths(PathSelectors.regex("/product/v1.0.*"))
                .build().apiInfo(ApiInfo(
                        "Product Service Documentation",
                        "Documentation automatically generated", "v1.0", null,
                        Contact("Aleksey Samoylov", "alekseysamoylov.com",
                                "alekseysamoylov89@gmail.com"), null, null))
    }
}
