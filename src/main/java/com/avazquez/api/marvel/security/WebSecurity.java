package com.avazquez.api.marvel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
/**
 * Configuration class for Spring Security settings.
 * <p>
 * Enables web security and method security for the application. Defines the security filter chain bean
 * that configures CSRF protection, session management, and request authorization.
 */
public class WebSecurity {
    
    /**
     * Defines the security filter chain for HTTP requests.
     * <p>
     * - Disables CSRF protection (for stateless APIs).
     * - Sets session management to stateless.
     * - Permits all requests (no authentication required).
     *
     * @param http the {@link HttpSecurity} to modify
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(config -> config.disable())
            .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(config -> config.anyRequest().permitAll())
            .build();
    }
}
