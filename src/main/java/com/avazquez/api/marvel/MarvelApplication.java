package com.avazquez.api.marvel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Main application class for the Marvel API integration service.
 *
 * <p>This Spring Boot application provides REST endpoints for accessing Marvel Comics data through
 * the official Marvel Developer API. It includes character and comic information with comprehensive
 * pagination, filtering, and documentation support.
 *
 * <p>Features:
 *
 * <ul>
 *   <li>Character search and details retrieval
 *   <li>Comic search with character filtering
 *   <li>Swagger/OpenAPI documentation
 *   <li>Environment-based configuration
 *   <li>Docker support with database integration
 * </ul>
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@EnableConfigurationProperties
public class MarvelApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarvelApplication.class, args);
  }
}
