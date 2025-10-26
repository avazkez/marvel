package com.avazquez.api.marvel.security.validator;

import com.avazquez.api.marvel.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator for user interaction log access control.
 *
 * <p>Ensures that a user can only access their own interaction logs by comparing the requested
 * username with the currently authenticated user's username.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Component("interactionLogValidator")
public class UserInteractionLogValidator {

  /** Injected authentication service for retrieving the logged-in user. */
  @Autowired private AuthenticationService authenticationService;

  /**
   * Validates that the given username matches the currently authenticated user.
   *
   * @param username the username to validate
   * @return {@code true} if the logged-in user matches the given username, {@code false} otherwise
   */
  public boolean validate(String username) {
    String userLoggedIn = authenticationService.getUserLoggedIn().getUsername();
    return userLoggedIn.equals(username);
  }
}
