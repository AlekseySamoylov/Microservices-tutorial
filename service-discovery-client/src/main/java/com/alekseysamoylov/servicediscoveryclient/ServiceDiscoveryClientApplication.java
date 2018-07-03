package com.alekseysamoylov.servicediscoveryclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceDiscoveryClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryClientApplication.class, args);
    }

}
