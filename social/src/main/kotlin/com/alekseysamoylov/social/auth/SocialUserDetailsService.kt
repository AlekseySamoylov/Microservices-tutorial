package com.alekseysamoylov.social.auth

import com.alekseysamoylov.social.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*


@Service
class SocialUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        return org.springframework.security.core.userdetails.User(username, user.password, true, true, true, true, Arrays.asList(SimpleGrantedAuthority("ROLE_USER")))
    }
}
