package com.avazquez.api.marvel.web.controller;

import com.avazquez.api.marvel.dto.security.LoginRequest;
import com.avazquez.api.marvel.dto.security.LoginResponse;
import com.avazquez.api.marvel.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for authentication endpoints (login and logout).
 * <p>Handles user authentication and session termination.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired private AuthenticationService authenticationService;

  /**
   * Authenticates a user and returns a JWT token.
   * <p>Delegates authentication to {@link AuthenticationService}.
   *
   * @param loginRequest the login request containing username and password
   * @return ResponseEntity containing the JWT token
   */
  @PostMapping("/authenticate")
  public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) {
    return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
  }

  /**
   * Logs out the current user and terminates the session.
   * <p>Delegates logout to {@link AuthenticationService}.
   */
  @PostMapping("/logout")
  public void logout() {
    authenticationService.logout();
  }
}
