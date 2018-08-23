package com.alekseysamoylov.hellojenkins.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloController {

    @RequestMapping("/")
    fun hello(): String {
        return "Hello Jenkins!"
    }
}
