package com.alekseysamoylov.productservice.controllers

import com.alekseysamoylov.orderservice.model.Product
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ProductController {
    private val log = LoggerFactory.getLogger(ProductController::class.java)

    @Value("\${product.prefix}")
    private lateinit var productPrefix: String

    @GetMapping("/")
    fun ping(): String {
        return "Hello kotlin product app"
    }

    @GetMapping("/product/1")
    fun getProduct(): Product {
        log.info("Product $productPrefix has found")
        return Product(1, "The best $productPrefix product")
    }

    @GetMapping("/product")
    fun getProducts(): List<Product> {
        return Arrays.asList(
                Product(1, "The best product"),
                Product(2, "The greatest product")
        )
    }


}
