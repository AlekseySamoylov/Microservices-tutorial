package com.alekseysamoylov.productservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ProductServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(ProductServiceApplication::class.java, *args)
}
