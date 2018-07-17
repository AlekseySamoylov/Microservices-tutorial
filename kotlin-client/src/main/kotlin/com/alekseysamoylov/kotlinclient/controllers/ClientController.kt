package com.alekseysamoylov.kotlinclient.controllers

import com.alekseysamoylov.kotlinclient.config.ClientConfiguration
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientController {
    private val log = LoggerFactory.getLogger(ClientController::class.java)

    @Value("\${eureka.instance.metadataMap.region}")
    private lateinit var region: String

    @Autowired
    private lateinit var clientConfiguration: ClientConfiguration

    @GetMapping("/ping", "/")
    fun ping(): String {
        log.info("Info logging Kotlin client in region $region")
        log.debug("Debug logging Kotlin client in region $region")
        return "Hello kotlin app: ${clientConfiguration.getConfigurableName()} in region $region";
    }
}
