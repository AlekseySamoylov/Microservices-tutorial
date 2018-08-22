package com.alekseysamoylov.social.config

import com.alekseysamoylov.social.auth.FacebookConnectionSignup
import com.alekseysamoylov.social.auth.FacebookSignInAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.social.config.annotation.SocialConfiguration
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository
import org.springframework.social.connect.web.ProviderSignInController


@Configuration
@Import(SocialConfiguration::class)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var socialUserDetailsService: UserDetailsService

    @Autowired
    private lateinit var connectionFactoryLocator: ConnectionFactoryLocator

    @Autowired
    private lateinit var usersConnectionRepository: UsersConnectionRepository

    @Autowired
    private lateinit var facebookConnectionSignup: FacebookConnectionSignup

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(socialUserDetailsService)
    }

    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login*", "/signin/**", "/signup./&&").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .and()
            .logout()
        // @formatter:on
    }

    @Bean
    fun providerSignInController(): ProviderSignInController {
        (usersConnectionRepository as InMemoryUsersConnectionRepository)
                .setConnectionSignUp(facebookConnectionSignup)

        return ProviderSignInController(
                connectionFactoryLocator,
                usersConnectionRepository,
                FacebookSignInAdapter())
    }

}
