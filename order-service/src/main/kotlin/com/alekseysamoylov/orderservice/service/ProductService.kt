package com.alekseysamoylov.orderservice.service

import com.alekseysamoylov.orderservice.feignservice.ProductClient
import com.alekseysamoylov.orderservice.model.Product
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {
    private val log = LoggerFactory.getLogger(ProductService::class.java)


    @Autowired
    private lateinit var productClient: ProductClient

    @HystrixCommand(fallbackMethod = "findAllFallback")
    fun findAll(): List<Product> {
        return productClient.findProducts()
    }

    @HystrixCommand(fallbackMethod = "findProductFallback")
    fun findProduct(productId: String): Product {
        return productClient.findProduct(productId)
    }

    fun saveProduct(product: Product): Product {
        return productClient.saveProduct(product)
    }

    fun findAllFallback(): List<Product> {
        log.error("Product service is not available")
        return ArrayList()
    }

    fun findProductFallback(productId: String): Product {
        return Product("0", "Default Product using Hystrix")
    }
}
