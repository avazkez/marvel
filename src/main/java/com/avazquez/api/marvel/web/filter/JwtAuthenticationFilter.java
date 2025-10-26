package com.avazquez.api.marvel.web.filter;

import com.avazquez.api.marvel.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter for authenticating requests using JWT tokens.
 *
 * <p>Extracts JWT from Authorization header, validates it, and sets authentication in the security
 * context.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  /** Service for JWT operations (token extraction, validation). */
  @Autowired private JwtService jwtService;

  /** Service for loading user details from username. */
  @Autowired private UserDetailsService userDetailsService;

  /**
   * Filters incoming requests and authenticates using JWT if present.
   *
   * <p>Sets authentication in the security context if token is valid.
   *
   * @param request the HTTP servlet request
   * @param response the HTTP servlet response
   * @param filterChain the filter chain
   * @throws ServletException if a servlet error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    // Header: Bearer <token>
    String authorizationHeader = request.getHeader("Authorization");

    if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = authorizationHeader.split(" ")[1];
    String subject = null;

    try {
      subject = jwtService.extractSubject(token);
    } catch (JwtException e) {
      filterChain.doFilter(request, response);
      return;
    }

    UserDetails user = userDetailsService.loadUserByUsername(subject);
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }
}
