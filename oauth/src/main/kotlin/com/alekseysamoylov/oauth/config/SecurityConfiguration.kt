package com.alekseysamoylov.oauth.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Description
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
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

    @Autowired
    private val calendarUserAuthenticationProvider: CalendarUserAuthenticationProvider? = null

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
     */
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
                .authenticationProvider(calendarUserAuthenticationProvider)
    }


    /**
     * BCryptPasswordEncoder password encoder
     * @return
     */
    @Description("Standard PasswordEncoder")
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(4)
    }


    /**
     * HTTP Security configuration
     *
     * <pre><http auto-config="true"></http></pre> is equivalent to:
     * <pre>
     * <http>
     * <form-login></form-login>
     * <http-basic></http-basic>
     * <logout></logout>
    </http> *
    </pre> *
     *
     * Which is equivalent to the following JavaConfig:
     *
     * <pre>
     * http.formLogin()
     * .and().httpBasic()
     * .and().logout();
    </pre> *
     *
     * @param http HttpSecurity configuration.
     * @throws Exception Authentication configuration exception
     *
     * @see [
     * Spring Security 3 to 4 migration](http://docs.spring.io/spring-security/site/migrate/current/3-to-4/html5/migrate-3-to-4-jc.html)
     */
    @Throws(Exception::class)
    protected override fun configure(http: HttpSecurity) {
        // Matching
        http.authorizeRequests()
                // FIXME: TODO: Allow anyone to use H2 (NOTE: NOT FOR PRODUCTION USE EVER !!! )
                .antMatchers("/admin/h2/**").permitAll()

                .antMatchers("/").permitAll()
                .antMatchers("/login/*").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/signup/*").permitAll()
                .antMatchers("/errors/**").permitAll()
                .antMatchers("/admin/*").access("hasRole('ADMIN') and isFullyAuthenticated()")
                .antMatchers("/events/").hasRole("ADMIN")
                .antMatchers("/**").hasRole("USER")

        // Login
        http.formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/login")
                .failureUrl("/login/form?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/default", true)
                .permitAll()

        // Logout
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/form?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .permitAll()

        // Anonymous
        http.anonymous()

        // CSRF is enabled by default, with Java Config
        http.csrf().disable()

        // Exception Handling
        http.exceptionHandling()
                .accessDeniedPage("/errors/403")


        // Enable <frameset> in order to use H2 web console
        http.headers().frameOptions().disable()
    }


    @Description("AuthenticationMnager that will generate an authentication token unlike {@link PreAuthenticatedAuthenticationProvider}")
    @Bean
    @DependsOn("defaultCalendarService")
    fun calendarUserAuthenticationProvider(
            calendarService: CalendarService,
            passwordEncoder: PasswordEncoder): CalendarUserAuthenticationProvider {
        return CalendarUserAuthenticationProvider(calendarService, passwordEncoder)
    }

/**
 * This is the equivalent to:
 * <pre>
 * <http pattern="/resources/**" security="none"></http>
 * <http pattern="/css/**" security="none"></http>
 * <http pattern="/webjars/**" security="none"></http>
</pre> *
 *
 * @param web
 * @throws Exception
*/
@Throws(Exception::class)
public override fun configure(web:WebSecurity?) {

// Ignore static resources and webjars from Spring Security
web!!.ignoring()
.antMatchers("/resources/**")
.antMatchers("/css/**")
.antMatchers("/webjars/**")

// Thymeleaf needs to use the Thymeleaf configured FilterSecurityInterceptor
// and not the default Filter from AutoConfiguration.
val http = getHttp()
web!!.postBuildAction({ web!!.securityInterceptor(http.getSharedObject<FilterSecurityInterceptor>(FilterSecurityInterceptor::class.java)) })
}

} // The End...
