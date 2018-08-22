package com.alekseysamoylov.social.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.social.UserIdSource
import org.springframework.social.config.annotation.EnableSocial
import org.springframework.social.config.annotation.SocialConfigurerAdapter
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.web.ConnectController
import org.springframework.social.connect.web.ProviderSignInUtils
import org.springframework.social.security.AuthenticationNameUserIdSource
import javax.sql.DataSource


@Configuration
@EnableSocial
class SocialConfig : SocialConfigurerAdapter() {
    @Autowired
    private lateinit var dataSource: DataSource

    override fun getUserIdSource(): UserIdSource {
        return AuthenticationNameUserIdSource()
    }

//    override fun getUsersConnectionRepository(connectionFactoryLocator: ConnectionFactoryLocator): UsersConnectionRepository {
//        // TODO (Aleksey Samoylov) replace inmemory...
//        return JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText())
//    }

    @Bean
    fun connectController(connectionFactoryLocator: ConnectionFactoryLocator, connectionRepository: ConnectionRepository): ConnectController {
        return ConnectController(connectionFactoryLocator, connectionRepository)
    }

    @Bean
    fun providerSignInUtils(connectionFactoryLocator: ConnectionFactoryLocator, usersConnectionRepository: UsersConnectionRepository): ProviderSignInUtils {
        return ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository)
    }
}
