package com.alekseysamoylov.graphql

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GraphqlApplication

fun main(args: Array<String>) {
    SpringApplication.run(GraphqlApplication::class.java, *args)
}
