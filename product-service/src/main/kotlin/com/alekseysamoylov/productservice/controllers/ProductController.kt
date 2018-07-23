package com.alekseysamoylov.productservice.controllers

import com.alekseysamoylov.productservice.entity.Product
import com.alekseysamoylov.productservice.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {
    private val log = LoggerFactory.getLogger(ProductController::class.java)

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Value("\${product.prefix}")
    private lateinit var productPrefix: String

    @GetMapping("/")
    fun ping(): String {
        return "Hello kotlin product app"
    }

    @GetMapping("/product/{productId}")
    fun getProduct(@PathVariable productId: String): Product {
        return productRepository.findOne(productId)
    }

    @PostMapping("/product")
    fun saveProduct(@RequestBody product: Product): Product {
        product.name = "$productPrefix ${product.name}"
        log.info("Product ${product.name} stored")
        return productRepository.save(product)
    }

    @GetMapping("/product")
    fun getProducts(): Collection<Product> {
        return productRepository.findAll()
    }


}
