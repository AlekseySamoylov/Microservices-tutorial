package com.alekseysamoylov.oauth.config

import com.alekseysamoylov.oauth.authentication.ProviderConnectionSignup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.social.connect.UsersConnectionRepository


@Configuration
class SocialConfig {

    @Autowired
    private lateinit var usersConnectionRepository: UsersConnectionRepository
    @Autowired
    private lateinit var providerConnectionSignup: ProviderConnectionSignup


}
