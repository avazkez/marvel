package com.avazquez.api.marvel.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for the Marvel Characters API.
 *
 * <p>This configuration class sets up Swagger/OpenAPI documentation with API information, contact
 * details, and server configurations.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class OpenApiConfig {

  @Value("${spring.application.name:Marvel API}")
  private String applicationName;

  /**
   * Configures the OpenAPI specification for the Marvel Characters API.
   *
   * @return OpenAPI configuration with API information, contact details, and servers
   */
  @Bean
  public OpenAPI marvelOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl("http://localhost:8080");
    devServer.setDescription("Development server");

    Server prodServer = new Server();
    prodServer.setUrl("https://api.marvel.yourcompany.com");
    prodServer.setDescription("Production server");

    Contact contact = new Contact();
    contact.setEmail("alex93vaz@hotmail.com");
    contact.setName("Alex Vazquez");
    contact.setUrl("https://github.com/alex93vaz");

    License mitLicense =
        new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info =
        new Info()
            .title("Marvel Characters API")
            .version("1.0.0")
            .contact(contact)
            .description(
                "A REST API for managing and retrieving Marvel character information. "
                    + "This API provides endpoints to search characters by various criteria, "
                    + "retrieve detailed character information, and manage character data.")
            .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
}
