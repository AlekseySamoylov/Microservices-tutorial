package com.alekseysamoylov.social

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SocialApplication

fun main(args: Array<String>) {
    runApplication<SocialApplication>(*args)
}
