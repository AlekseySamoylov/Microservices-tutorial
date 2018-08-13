package com.alekseysamoylov.oauth.authentication

import com.alekseysamoylov.oauth.core.authorities.createAuthorities
import com.alekseysamoylov.oauth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class MyUserAuthenticationProvider
@Autowired constructor(
        private val userService: UserService,
        private val passwordEncoder: PasswordEncoder) : AuthenticationProvider {


    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication as UsernamePasswordAuthenticationToken
        val email = token.name
        val user = (if (email == null) null else userService.findUserByEmail(email))
                ?: throw UsernameNotFoundException("Invalid username/password")
// Database Password already encrypted:
        val password = user.password

        val passwordsMatch = passwordEncoder.matches(token.credentials.toString(), password)

        if (!passwordsMatch) {
            throw BadCredentialsException("Invalid username/password")
        }
        val authorities = createAuthorities(user)
        return UsernamePasswordAuthenticationToken(user, password, authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java == authentication
    }
}
