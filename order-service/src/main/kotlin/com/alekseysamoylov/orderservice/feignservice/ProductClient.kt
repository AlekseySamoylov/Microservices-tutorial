package com.alekseysamoylov.orderservice.feignservice

import com.alekseysamoylov.orderservice.model.Product
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "product-service")
interface ProductClient {

    @GetMapping("/product")
    fun findProducts(): List<Product>

    @GetMapping("/product/{productId}")
    fun findProduct(@RequestParam(name = "productId") productId: String): Product

    @PostMapping("/product")
    fun saveProduct(product: Product): Product
}
