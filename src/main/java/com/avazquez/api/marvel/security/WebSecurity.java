package com.avazquez.api.marvel.security;

import com.avazquez.api.marvel.web.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for Spring Security settings.
 *
 * <p>Enables web security and method security for the application. Defines the security filter
 * chain bean that configures CSRF protection, session management, and request authorization.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurity {

  /** Injected JWT authentication filter for processing JWT tokens. */
  @Autowired private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired private AuthenticationProvider authenticationProvider;

  /**
   * Defines the security filter chain for HTTP requests.
   *
   * <p>- Disables CSRF protection (for stateless APIs). - Sets session management to stateless. -
   * Permits requests to '/error' endpoint for all users. - Requires authentication for all other
   * endpoints.
   *
   * @param http the {@link HttpSecurity} to modify
   * @return the configured {@link SecurityFilterChain}
   * @throws Exception if an error occurs during configuration
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors(Customizer.withDefaults())
        .csrf(config -> config.disable())
        .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(
            config -> {
              config
                  .requestMatchers(
                      "/error",
                      "/auth/login",
                      "/auth/logout",
                      "/swagger-ui.html",
                      "/swagger-ui/**",
                      "/v3/api-docs",
                      "/api-docs",
                      "/v3/api-docs/**",
                      "/swagger-resources/**",
                      "/webjars/**")
                  .permitAll();
              config.anyRequest().authenticated();
            })
        .authenticationProvider(authenticationProvider)
        .build();
  }
}
