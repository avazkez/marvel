package com.avazquez.api.marvel.security;

import com.avazquez.api.marvel.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for security-related beans.
 *
 * <p>Provides beans for authentication manager, authentication provider, password encoder, and user
 * details service.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class SecurityBeansInjector {

  /** Injected authentication configuration for building the authentication manager. */
  @Autowired private AuthenticationConfiguration authenticationConfiguration;

  /** Injected user repository for loading user details. */
  @Autowired private UserRepository userRepository;

  /**
   * Bean for the Spring Security authentication manager.
   *
   * @return the authentication manager
   * @throws Exception if authentication manager cannot be created
   */
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Bean for the authentication provider using DAO and custom user details service.
   *
   * @return the authentication provider
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  /**
   * Bean for the password encoder (BCrypt).
   *
   * @return the password encoder
   */
  @Bean
  @Primary
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Bean for the user details service, loads user by username from repository.
   *
   * @return the user details service
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      return userRepository
          .findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    };
  }
}
