package com.alekseysamoylov.productservice.controllers

import com.alekseysamoylov.productservice.entity.Product
import com.alekseysamoylov.productservice.repository.ProductRepository
import org.javasimon.aop.Monitored
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController {
    private val log = LoggerFactory.getLogger(ProductController::class.java)

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Value("\${product.prefix}")
    private lateinit var productPrefix: String

    @GetMapping("/v1.1/{productId}")
    fun getProduct(@PathVariable productId: String): Product {
        val product = productRepository.findOne(productId)
        product.name += getStockName()
        return product
    }

    @PostMapping("/v1.1")
    fun saveProduct(@RequestBody product: Product): Product {
        product.name = "$productPrefix ${product.name}"
        log.info("Product ${product.name} stored")
        return productRepository.save(product)
    }

    @Monitored
    @GetMapping("/v1.1")
    fun getProducts(): Collection<Product> {
        val products = productRepository.findAll()
        Thread.sleep((1000 + (Math.random() * 150)).toLong())
        products.forEach { product ->
            product.name += getStockName()
        }
        return products
    }

    @GetMapping("/v1.0")
    fun getProductsDeprecated(): Collection<Product> {
        val products = productRepository.findAll()
        products.forEach { product ->
            product.name += "${getStockName()} DEPRECATED API"
        }
        return products
    }

    fun getStockName(): String {
        return " from  $productPrefix stock"
    }


}
