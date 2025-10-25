package com.avazquez.api.marvel.service;

import com.avazquez.api.marvel.dto.security.LoginRequest;
import com.avazquez.api.marvel.dto.security.LoginResponse;
import com.avazquez.api.marvel.persistence.entity.User;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Service for handling authentication logic, including login and logout operations.
 * <p>Uses Spring Security and JWT for authentication.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Service
public class AuthenticationService {

  /**
   * Injected HttpSecurity for configuring logout and authentication.
   */
  @Autowired private HttpSecurity http;

  /**
   * Injected UserDetailsService for loading user information.
   */
  @Autowired private UserDetailsService userDetailsService;

  /**
   * Injected AuthenticationManager for authenticating credentials.
   */
  @Autowired private AuthenticationManager authenticationManager;

  /**
   * Injected JwtService for generating JWT tokens.
   */
  @Autowired private JwtService jwtService;

  /**
   * Authenticates a user and generates a JWT token.
   * <p>Loads user details, authenticates credentials, and issues JWT.
   *
   * @param loginRequest the login request containing username and password
   * @return LoginResponse containing the JWT token
   * @throws RuntimeException if authentication fails
   */
  public LoginResponse authenticate(LoginRequest loginRequest) {

    UserDetails user = userDetailsService.loadUserByUsername(loginRequest.username());

    Authentication authentication =
        new UsernamePasswordAuthenticationToken(
            user, loginRequest.password(), user.getAuthorities());
    authenticationManager.authenticate(authentication);

    String jwt = jwtService.generateToken(user, generateExtraClaims(user));

    return new LoginResponse(jwt);
  }

  /**
   * Generates extra claims for JWT token based on user details.
   * <ul>
   *   <li>roles: user's role name</li>
   *   <li>authorities: list of granted authorities</li>
   * </ul>
   *
   * @param user the authenticated user
   * @return map of extra claims for JWT
   */
  private Map<String, Object> generateExtraClaims(UserDetails user) {
    Map<String, Object> extraClaims = new HashMap<>();
    String roleName = ((User) user).getRole().getName().name();
    extraClaims.put("roles", roleName);
    extraClaims.put(
        "authorities",
        user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));
    return extraClaims;
  }

  /**
   * Logs out the current user, clears authentication, and invalidates session.
   * <p>Deletes session cookies and handles logout exceptions.
   *
   * @throws RuntimeException if logout fails
   */
  public void logout() {
    try {
      http.logout(
          logoutConfig -> {
            logoutConfig
                .deleteCookies("SESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true);
          });
    } catch (Exception e) {
      throw new RuntimeException("Error during logout", e);
    }
  }
}
