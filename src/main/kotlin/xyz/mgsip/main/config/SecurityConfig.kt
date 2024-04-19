package xyz.mgsip.main.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import java.util.*

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity):SecurityFilterChain{

return http
    .authorizeHttpRequests{req -> req.requestMatchers("/api/**").permitAll()
        .anyRequest().authenticated()}
    .formLogin{login -> login.disable()}
    .csrf{csrf -> csrf.disable()}
    .cors { cors ->
        cors.configurationSource(CorsConfigurationSource {
            val config = CorsConfiguration()
            config.allowedOrigins = listOf("*")
            config.allowedMethods = listOf("*")
            config.allowCredentials = false
            config.allowedHeaders = listOf("*")
            config.maxAge = 3600L // 1시간
            config
        })}
    .build()
    }
}