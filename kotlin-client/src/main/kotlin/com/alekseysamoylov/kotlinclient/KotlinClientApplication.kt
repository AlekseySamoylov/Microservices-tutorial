package com.alekseysamoylov.kotlinclient

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinClientApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinClientApplication::class.java, *args)
}
