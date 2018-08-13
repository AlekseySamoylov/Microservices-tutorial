package com.alekseysamoylov.oauth

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OauthApplication

fun main(args: Array<String>) {
    SpringApplication.run(OauthApplication::class.java, *args)
}
