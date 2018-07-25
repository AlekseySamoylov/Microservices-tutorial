package com.alekseysamoylov.orderservice.feignservice

import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "service-kotlin-client")
interface ClientsClient {

    @GetMapping("/client/{clientId}")
    fun findClient(@RequestParam(name = "clientId") productId: String): String
}
