package com.alekseysamoylov.servicediscoveryclient.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Value("${eureka.instance.metadataMap.region}")
    private String region;

    @GetMapping("/ping")
    public String ping() {
        LOGGER.info("Info access to client server with region " + region);
        LOGGER.debug("Debug access to client server with region " + region);
        return "The region is " + region;
    }
}
