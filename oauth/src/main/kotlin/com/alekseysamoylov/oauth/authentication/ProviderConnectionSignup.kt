package com.alekseysamoylov.oauth.authentication

import com.alekseysamoylov.oauth.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionSignUp

class ProviderConnectionSignup : ConnectionSignUp {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun execute(connection: Connection<*>?): String {
        val user = createCalendarUserFromProvider(connection!!)

        userRepository.createUser(user)

        return user.email
    }

}
