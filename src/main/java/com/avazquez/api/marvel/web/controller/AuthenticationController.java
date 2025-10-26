package com.avazquez.api.marvel.web.controller;

import com.avazquez.api.marvel.dto.security.LoginRequest;
import com.avazquez.api.marvel.dto.security.LoginResponse;
import com.avazquez.api.marvel.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for authentication endpoints (login and logout).
 *
 * <p>Handles user authentication and session termination.
 *
 * <p>Security:
 *
 * <ul>
 *   <li><b>POST /auth/login</b>: Public endpoint, returns JWT token for authenticated users
 *   <li><b>POST /auth/logout</b>: Public endpoint, terminates session for current user
 * </ul>
 *
 * <p>Authentication is performed using JWT tokens returned by the login endpoint.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user login and logout")
public class AuthenticationController {

  @Autowired private AuthenticationService authenticationService;

  /**
   * Authenticates a user and returns a JWT token.
   *
   * <p>Delegates authentication to {@link AuthenticationService}.
   *
   * @param loginRequest the login request containing username and password
   * @return ResponseEntity containing the JWT token
   */
  @PreAuthorize("permitAll")
  @Operation(
      summary = "Authenticate user and return JWT token",
      description =
          "Authenticates a user with username and password, returns a JWT token on success.",
      tags = {"Authentication"})
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Successful authentication",
        content = @Content(schema = @Schema(implementation = LoginResponse.class))),
    @ApiResponse(responseCode = "403", description = "Invalid credentials", content = @Content)
  })
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) {
    return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
  }

  /**
   * Logs out the current user and terminates the session.
   *
   * <p>Delegates logout to {@link AuthenticationService}.
   */
  @PreAuthorize("permitAll")
  @Operation(
      summary = "Logout current user",
      description = "Logs out the current user and terminates the session.",
      tags = {"Authentication"})
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Successful logout", content = @Content),
    @ApiResponse(responseCode = "403", description = "User not authenticated", content = @Content)
  })
  @PostMapping("/logout")
  public void logout() {
    authenticationService.logout();
  }
}
