package com.alekseysamoylov.orderservice.controllers

import com.alekseysamoylov.orderservice.feignservice.ProductClient
import com.alekseysamoylov.orderservice.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
class OrderController {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Autowired
    private lateinit var productClient: ProductClient

    @GetMapping("/")
    fun welcome(): String {
        return "Welcome to The Order Service"
    }

    @GetMapping("/order-product")
    fun orderProduct(): String {
        val product = restTemplate.getForObject("http://product-service/product/1", Product::class.java)
        return "Service has ordered a product: $product"
    }

    @GetMapping("/order-feign-product/{productId}")
    fun orderFeignProduct1(@PathVariable productId: String): String {
        val product = productClient.findProduct(productId)
        return "Service has ordered a product: $product using Feign"
    }

    @PostMapping("/save-product")
    fun saveProduct(@RequestBody product: Product): Product {
        return productClient.saveProduct(product)
    }
}
