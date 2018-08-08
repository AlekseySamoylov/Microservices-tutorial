package com.alekseysamoylov.apigateway.documentation

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import springfox.documentation.swagger.web.SwaggerResource
import springfox.documentation.swagger.web.SwaggerResourcesProvider


@Component
@Primary
@EnableAutoConfiguration
class DocumentationController : SwaggerResourcesProvider {

    override fun get(): MutableList<SwaggerResource> {
        val resources = mutableListOf<SwaggerResource>()
        // TODO (Aleksey Samoylov) move property to Spring Cloud Config Server
        resources.add(swaggerResource("product-service", "/api/product/v2/api-docs?group=product-api-1.0", "1.0"))
        resources.add(swaggerResource("product-service", "/api/product/v2/api-docs?group=product-api-1.1", "1.1"))
        resources.add(swaggerResource("order-service", "/api/order/v2/api-docs", "1.1"))
        resources.add(swaggerResource("kotlin-client", "/api/kotlin-client/v2/api-docs", "2.0"))
        resources.add(swaggerResource("java-client", "/api/java-client/v2/api-docs", "2.0"))
        return resources
    }


    private fun swaggerResource(name: String, location: String, version: String): SwaggerResource {
        val swaggerResource = SwaggerResource()
        swaggerResource.name = name
        swaggerResource.location = location
        swaggerResource.swaggerVersion = version
        return swaggerResource
    }
}
