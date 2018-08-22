package com.alekseysamoylov.social

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.social.config.annotation.EnableSocial
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableSocial
@EnableWebMvc
@EnableWebSecurity
@SpringBootApplication
class SocialApplication : SpringBootServletInitializer()
fun main(args: Array<String>) {
    SpringApplication.run(SocialApplication::class.java, *args)
}
