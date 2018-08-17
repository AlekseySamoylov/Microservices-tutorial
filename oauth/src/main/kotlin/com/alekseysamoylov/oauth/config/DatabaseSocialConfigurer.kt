package com.alekseysamoylov.oauth.config

import org.springframework.core.env.Environment
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer
import org.springframework.social.config.annotation.SocialConfigurerAdapter
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository
import org.springframework.social.github.connect.GitHubConnectionFactory
import org.springframework.social.google.connect.GoogleConnectionFactory
import javax.sql.DataSource


class DatabaseSocialConfigurer(private val dataSource: DataSource) : SocialConfigurerAdapter() {

    override fun getUsersConnectionRepository(connectionFactoryLocator: ConnectionFactoryLocator): UsersConnectionRepository {
        val textEncryptor = Encryptors.noOpText()
        return JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, textEncryptor)
    }

    override fun addConnectionFactories(config: ConnectionFactoryConfigurer, env: Environment) {
        super.addConnectionFactories(config, env)

        // Configured through AutoConfiguration:
        //        config.addConnectionFactory(new TwitterConnectionFactory(
        //            env.getProperty("spring.social.twitter.appId"),
        //            env.getProperty("spring.social.twitter.appSecret")));
        //        config.addConnectionFactory(new FacebookConnectionFactory(
        //            env.getProperty("spring.social.facebook.appId"),
        //            env.getProperty("spring.social.facebook.appSecret")));
        //        config.addConnectionFactory(new LinkedInConnectionFactory(
        //            env.getProperty("spring.social.linkedin.appId"),
        //            env.getProperty("spring.social.linkedin.appSecret")));

        // Adding GitHub Connection with properties from application.yml
        config.addConnectionFactory(GitHubConnectionFactory(
                env.getProperty("spring.social.github.appId"),
                env.getProperty("spring.social.github.appSecret")))

        // Adding Google Connection with properties from application.yml
        config.addConnectionFactory(GoogleConnectionFactory(
                env.getProperty("spring.social.google.appId"),
                env.getProperty("spring.social.google.appSecret")))
    }

} // The End...
