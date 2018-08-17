package com.alekseysamoylov.oauth.config

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Description
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.UserDetailsManager


@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    private val logger = LoggerFactory
            .getLogger(SecurityConfiguration::class.java)

    // TODO (Aleksey Samoylov) Reimport this...
//    @Autowired
//    private val calendarUserAuthenticationProvider: CalendarUserAuthenticationProvider? = null

    /**
     * Configure AuthenticationManager.
     *
     * NOTE:
     * Due to a known limitation with JavaConfig:
     * [
 * https://jira.spring.io/browse/SPR-13779](https://jira.spring.io/browse/SPR-13779)
     *
     * We cannot use the following to expose a [UserDetailsManager]
     * <pre>
     * http.authorizeRequests()
    </pre> *
     *
     * In order to expose [UserDetailsManager] as a bean, we must create  @Bean
     *
     * @see {@link super.userDetailsService
     * @see {@link com.packtpub.springsecurity.service.DefaultCalendarService}
     *
     *
     * @param auth       AuthenticationManagerBuilder
     * @throws Exception Authentication exception
    //     */
//    @Throws(Exception::class)
//    override fun configure(auth: AuthenticationManagerBuilder?) {
//        auth!!
//                .authenticationProvider(calendarUserAuthenticationProvider)
//    }


    /**
     * BCryptPasswordEncoder password encoder
     * @return
     */
    @Description("Standard PasswordEncoder")
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(4)
    }

}
