package com.alekseysamoylov.productservice.controllers

import com.alekseysamoylov.productservice.model.Product
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.atomic.AtomicLong

@RestController
class ProductController {
    private val log = LoggerFactory.getLogger(ProductController::class.java)

    private var products: MutableMap<Long, Product> = mutableMapOf(1L to Product(1, "New Product"))
    private val counter: AtomicLong = AtomicLong(1)

    @Value("\${product.prefix}")
    private lateinit var productPrefix: String

    @GetMapping("/")
    fun ping(): String {
        return "Hello kotlin product app"
    }

    @GetMapping("/product/{productId}")
    fun getProduct(@PathVariable productId: Long): Product {
        val product = products.get(productId)
        if (product != null) {
            return product
        } else {
            return Product()
        }
    }

    @PostMapping("/product")
    fun saveProduct(@RequestBody product: Product): Product {
        val id = counter.incrementAndGet()
        product.id = id
        product.name = "$productPrefix ${product.name}"
        products.put(id, product)
        return product
    }

    @GetMapping("/product")
    fun getProducts(): List<Product> {
        return Arrays.asList(
                Product(1, "The best product"),
                Product(2, "The greatest product")
        )
    }


}
