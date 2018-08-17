package com.alekseysamoylov.servicediscoveryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClients({
        @RibbonClient(name = "product-service")
})
public class ServiceDiscoveryClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryClientApplication.class, args);
    }

}
