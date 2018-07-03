package com.alekseysamoylov.servicediscoveryclient.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Value("${eureka.instance.metadataMap.region}")
    private String region;

    @GetMapping("/ping")
    public String ping() {
        return "The region is " + region;
    }
}
