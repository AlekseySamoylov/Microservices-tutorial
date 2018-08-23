package com.alekseysamoylov.hellojenkins

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloJenkinsApplication

fun main(args: Array<String>) {
    runApplication<HelloJenkinsApplication>(*args)
}
