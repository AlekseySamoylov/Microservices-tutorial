package com.alekseysamoylov.kotlinclient.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientController {

    @Value("\${eureka.instance.metadataMap.region}")
    private lateinit var region: String

    @GetMapping("/ping")
    fun ping(): String {
        return "Hello kotlin region $region";
    }

}
