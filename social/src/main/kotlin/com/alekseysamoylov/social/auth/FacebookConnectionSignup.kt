package com.alekseysamoylov.social.auth

import com.alekseysamoylov.social.entity.User
import com.alekseysamoylov.social.repository.UserRepository
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionSignUp
import org.springframework.stereotype.Service


@Service
class FacebookConnectionSignup : ConnectionSignUp {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun execute(connection: Connection<*>): String {
        val user = User()
        user.username = connection.displayName
        user.password = randomAlphabetic(8)
        userRepository.save(user)
        return user.username
    }
}
