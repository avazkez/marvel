package com.avazquez.api.marvel.web.interceptor;

import com.avazquez.api.marvel.persistence.entity.UserInteractionLog;
import com.avazquez.api.marvel.persistence.repository.UserInteractionLogRepository;
import com.avazquez.api.marvel.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor for logging user interactions with Marvel API endpoints.
 *
 * <p>This interceptor logs requests to <code>/api/comics</code> and <code>/api/characters</code>
 * endpoints, capturing details such as HTTP method, URL, username, remote address, and timestamp.
 *
 * <p>Uses {@link UserInteractionLogRepository} to persist logs and {@link AuthenticationService} to
 * obtain user details.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Component
public class UserInteractionInterceptor implements HandlerInterceptor {

  /** Repository for saving user interaction logs. */
  @Autowired private UserInteractionLogRepository userInteractionLogRepository;

  /** Service for retrieving authenticated user details. */
  @Autowired @Lazy private AuthenticationService authenticationService;

  /**
   * Intercepts requests to log user interactions for specific Marvel API endpoints.
   *
   * <p>Logs requests to <code>/api/comics</code> and <code>/api/characters</code> endpoints.
   * Persists log details including HTTP method, URL, username, remote address, and timestamp.
   * Throws an exception if log persistence fails.
   *
   * @param request the HTTP servlet request
   * @param response the HTTP servlet response
   * @param handler the handler object
   * @return {@code true} to continue processing the request
   * @throws Exception if an error occurs while saving the log
   */
  @Override
  public boolean preHandle(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler)
      throws Exception {

    System.out.println("Intercepting request: " + request.getRequestURL());

    String requestURI = request.getRequestURI();

    if (requestURI.startsWith("/api/comics") || requestURI.startsWith("/api/characters")) {

      UserInteractionLog userLog = new UserInteractionLog();
      userLog.setHttpMethod(request.getMethod());
      userLog.setUrl(request.getRequestURL().toString());

      UserDetails userDetails = authenticationService.getUserLoggedIn();

      userLog.setUsername(userDetails.getUsername());
      userLog.setRemoteAddress(request.getRemoteAddr());
      userLog.setTimestamp(LocalDateTime.now());

      try {
        userInteractionLogRepository.save(userLog);
        return true;
      } catch (Exception e) {
        throw new Exception("Error saving user interaction log", e);
      }
    }

    return true;
  }
}
