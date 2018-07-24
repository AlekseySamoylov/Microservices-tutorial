package com.alekseysamoylov.orderservice.service

import com.alekseysamoylov.orderservice.feignservice.ProductClient
import com.alekseysamoylov.orderservice.model.Product
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

/**
 * also you can use:
 * \@CacheEvict - for clearing the cache
 * \@Cacheable - for use cache in method invocation
 */
@Service
class ProductService {
    private val log = LoggerFactory.getLogger(ProductService::class.java)

    @Autowired
    private lateinit var productClient: ProductClient

    @Autowired
    private lateinit var cacheManager: CacheManager

    @CachePut("allProducts")
    @HystrixCommand(fallbackMethod = "findAllByKindFallback")
    fun findAllByKind(kind: String): List<Product> {
        return productClient.findProducts()
    }

    @Suppress("UNCHECKED_CAST")
    fun findAllByKindFallback(kind: String): List<Product> {
        log.error("Product service is not available, use cache")
        return cacheManager.getCache("allProducts")
                .get(kind).get() as List<Product>
    }

    @CachePut("product")
    @HystrixCommand(fallbackMethod = "findProductFallback")
    fun findProduct(productId: String): Product {
        return productClient.findProduct(productId)
    }

    fun findProductFallback(productId: String): Product {
        log.error("Product service is not available, use cache")
        val product: Product = cacheManager.getCache("product").get(productId).get() as Product
        if (product.id != null) {
            return product
        } else {
            log.error("Product cache is not available, return default product")
            return Product("0", "Default Product using Hystrix")
        }
    }

    fun saveProduct(product: Product): Product {
        return productClient.saveProduct(product)
    }
}
