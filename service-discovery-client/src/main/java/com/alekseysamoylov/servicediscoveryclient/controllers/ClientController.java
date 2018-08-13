package com.alekseysamoylov.servicediscoveryclient.controllers;

import com.alekseysamoylov.servicediscoveryclient.config.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Value("${eureka.instance.metadataMap.region}")
    private String region;

    @Autowired
    private ClientConfiguration clientConfiguration;

    @GetMapping({"/ping", "/"})
    public String ping() {
        final String appName = clientConfiguration.getConfigurableName();
        LOGGER.info("Info access to client server " + appName + " with region " + region);
        LOGGER.debug("Debug access to client server " + appName + " with region " + region);
        return "The app: " + appName + " in region: " + region;
    }
}
