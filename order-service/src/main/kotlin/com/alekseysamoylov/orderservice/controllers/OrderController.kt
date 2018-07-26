package com.alekseysamoylov.orderservice.controllers

import com.alekseysamoylov.orderservice.model.Product
import com.alekseysamoylov.orderservice.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
class OrderController {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Autowired
    private lateinit var productService: ProductService

    @Value("\${order.prefix}")
    private lateinit var orderPrefix: String

    @GetMapping("/")
    fun welcome(): String {
        return "Welcome to The Order Service"
    }

    @GetMapping("/order-product")
    fun orderProduct(): String {
        val product = restTemplate.getForObject("http://product-service/product/1", Product::class.java)
        return "Service $orderPrefix Order Service has ordered a product: $product"
    }

    @GetMapping("/order-feign-product/{productId}")
    fun orderFeignProduct1(@PathVariable productId: String): String {
        val product = productService.findProduct(productId)
        return "Service $orderPrefix Order Service has ordered a product: $product using Feign"
    }

    @PostMapping("/save-product")
    fun saveProduct(@RequestBody product: Product): Product {
        product.name = "Ordered in $orderPrefix Order Service product: ${product.name}"
        return productService.saveProduct(product)
    }

    @GetMapping("/all-products")
    fun displayAll(): String {
        val products = productService.findAllByKind("all")
        return "The list of product in the $orderPrefix Order Service: \n" +
                products.map { product -> product.name }.joinToString(separator = ";\n")
    }
}
