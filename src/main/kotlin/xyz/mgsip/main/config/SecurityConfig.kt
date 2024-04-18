package xyz.mgsip.main.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity):SecurityFilterChain{

return http
    .authorizeHttpRequests{req -> req.requestMatchers("/api/**").permitAll()
        .anyRequest().authenticated()}
    .formLogin{login -> login.disable()}
    .csrf{csrf -> csrf.disable()}
    .build()
    }
}