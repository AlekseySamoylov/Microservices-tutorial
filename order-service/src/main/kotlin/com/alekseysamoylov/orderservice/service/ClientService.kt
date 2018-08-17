package com.alekseysamoylov.orderservice.service

import com.alekseysamoylov.orderservice.feignservice.ClientsClient
import com.alekseysamoylov.orderservice.model.Product
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ClientService {
    private val log = LoggerFactory.getLogger(ClientService::class.java)
    private val CLIENT_ID = "1"

    @Autowired
    private lateinit var clientsClient: ClientsClient

    @HystrixCommand(fallbackMethod = "addClientsToAllFallback")
    fun addClientsToAll(products: List<Product>): List<Product> {
        products.forEach { product ->
            addClientToProduct(product)
        }
        return products
    }

    fun addClientsToAllFallback(products: List<Product>): List<Product> {
        products.forEach { product ->
            addClientToProductFallback(product)
        }
        return products
    }

    @HystrixCommand(fallbackMethod = "addClientToProductFallback")
    fun addClientToProduct(product: Product): Product {
        product.name += " for client ${clientsClient.findClient(CLIENT_ID)}"
        return product
    }

    fun addClientToProductFallback(product: Product): Product {
        product.name += " for client Fallback from Fallback region"
        return product
    }

}
