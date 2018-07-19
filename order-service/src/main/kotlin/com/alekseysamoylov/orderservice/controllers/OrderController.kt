package com.alekseysamoylov.orderservice.controllers

import com.alekseysamoylov.orderservice.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class OrderController {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @GetMapping("/order-product")
    fun orderProduct(): String {
        val product = restTemplate.getForObject("http://product-service/product/1", Product::class.java)
        return "Service has ordered a product: $product"
    }

}
