package com.avazquez.api.marvel.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration class for generic bean definitions used across the Marvel API application.
 *
 * <p>Provides reusable beans such as RestTemplate for dependency injection.
 *
 * @author Alex Vazquez
 * @since 1.0
 */
@Configuration
public class GenericBeansInjector {

  /**
   * Provides a singleton RestTemplate bean for HTTP client operations.
   *
   * @return a configured RestTemplate instance
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
