package com.avazquez.api.marvel.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Web MVC configuration for registering interceptors.
 *
 * <p>This configuration class adds the {@link UserInteractionInterceptor} to the application's
 * interceptor registry.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

  /** Injected interceptor for logging user interactions. */
  @Autowired private UserInteractionInterceptor userInteractionInterceptor;

  /**
   * Registers the {@link UserInteractionInterceptor} with the application's interceptor registry.
   *
   * @param registry the interceptor registry
   */
  @Override
  public void addInterceptors(@NonNull InterceptorRegistry registry) {
    registry.addInterceptor(userInteractionInterceptor);
  }
}
