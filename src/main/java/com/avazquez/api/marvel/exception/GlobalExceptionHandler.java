// TODO: Re-add GlobalExceptionHandler after resolving Swagger conflict

// package com.avazquez.api.marvel.exception;

// import com.avazquez.api.marvel.dto.exception.ApiErrorDto;
// import jakarta.servlet.http.HttpServletRequest;
// import java.nio.file.AccessDeniedException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
// import org.springframework.web.client.HttpClientErrorException;
// import org.springframework.web.context.request.WebRequest;

// /**
//  * Global exception handler for REST API errors.
//  *
//  * <p>This class intercepts exceptions thrown by controllers and provides consistent error
// responses
//  * using {@link ApiErrorDto}. It handles common cases such as HTTP client errors, access denied,
//  * authentication failures, and generic server errors.
//  *
//  * <ul>
//  *   <li><b>HttpClientErrorException</b>: External API errors
//  *   <li><b>AccessDeniedException</b>: Forbidden access
//  *   <li><b>AuthenticationCredentialsNotFoundException</b>: Unauthorized access
//  *   <li><b>Exception</b>: All other server errors
//  * </ul>
//  *
//  * <p>Returns structured error details including message, HTTP method, and request URL.
//  *
//  * @author Alex Vazquez
//  * @version 1.0
//  * @since 1.0
//  */
// @RestControllerAdvice
// public class GlobalExceptionHandler {

//   /**
//    * Handles all exceptions thrown by controllers and delegates to specific handlers based on
// type.
//    *
//    * @param exception the thrown exception
//    * @param request the HTTP servlet request
//    * @param webRequest the web request context
//    * @return ResponseEntity with structured error details
//    */
//   @ExceptionHandler(Exception.class)
//   public ResponseEntity<ApiErrorDto> handleGeneralExceptions(
//       Exception exception, HttpServletRequest request, WebRequest webRequest) {
//     if (exception instanceof HttpClientErrorException) {
//       return this.handleHttpClientErrorException(
//           (HttpClientErrorException) exception, request, webRequest);
//     } else if (exception instanceof AccessDeniedException) {
//       return this.handleAccessDeniedException(
//           (AccessDeniedException) exception, request, webRequest);
//     } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
//       return this.handleAuthenticationCredentialsNotFoundException(
//           (AuthenticationCredentialsNotFoundException) exception, request, webRequest);
//     } else {
//       return this.handleGenericException(exception, request, webRequest);
//     }
//   }

//   /**
//    * Handles errors from external API calls (HttpClientErrorException).
//    *
//    * @param exception the HttpClientErrorException thrown
//    * @param request the HTTP servlet request
//    * @param webRequest the web request context
//    * @return ResponseEntity with error details and external API message
//    */
//   private ResponseEntity<ApiErrorDto> handleHttpClientErrorException(
//       HttpClientErrorException exception, HttpServletRequest request, WebRequest webRequest) {
//     String message = null;

//     if (exception instanceof HttpClientErrorException.Forbidden) {
//       message = "Access to the external API resource is forbidden.";
//     } else if (exception instanceof HttpClientErrorException.Unauthorized) {
//       message = "Unauthorized access to the external API.";
//     } else if (exception instanceof HttpClientErrorException.NotFound) {
//       message = "The requested resource was not found in the external API.";
//     } else if (exception instanceof HttpClientErrorException.Conflict) {
//       message = "There is a conflict with the current state of the resource in the external
// API.";
//     } else {
//       message = "Unexpected error occurred when communicating with external API.";
//     }

//     ApiErrorDto apiErrorDto =
//         new ApiErrorDto(
//             message,
//             exception.getMessage(),
//             request.getMethod(),
//             request.getRequestURL().toString());
//     return ResponseEntity.status(exception.getStatusCode()).body(apiErrorDto);
//   }

//   /**
//    * Handles forbidden access errors (AccessDeniedException).
//    *
//    * @param exception the AccessDeniedException thrown
//    * @param request the HTTP servlet request
//    * @param webRequest the web request context
//    * @return ResponseEntity with error details and forbidden status
//    */
//   private ResponseEntity<ApiErrorDto> handleAccessDeniedException(
//       AccessDeniedException exception, HttpServletRequest request, WebRequest webRequest) {
//     ApiErrorDto apiErrorDto =
//         new ApiErrorDto(
//             "You don't have permissions to access this resource.",
//             exception.getMessage(),
//             request.getMethod(),
//             request.getRequestURL().toString());
//     return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiErrorDto);
//   }

//   /**
//    * Handles unauthorized access errors (AuthenticationCredentialsNotFoundException).
//    *
//    * @param exception the AuthenticationCredentialsNotFoundException thrown
//    * @param request the HTTP servlet request
//    * @param webRequest the web request context
//    * @return ResponseEntity with error details and unauthorized status
//    */
//   private ResponseEntity<ApiErrorDto> handleAuthenticationCredentialsNotFoundException(
//       AuthenticationCredentialsNotFoundException exception,
//       HttpServletRequest request,
//       WebRequest webRequest) {
//     ApiErrorDto apiErrorDto =
//         new ApiErrorDto(
//             "You don't have permissions to access this resource.",
//             exception.getMessage(),
//             request.getMethod(),
//             request.getRequestURL().toString());
//     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiErrorDto);
//   }

//   /**
//    * Handles all other unexpected server errors.
//    *
//    * @param exception the thrown exception
//    * @param request the HTTP servlet request
//    * @param webRequest the web request context
//    * @return ResponseEntity with generic error details and internal server error status
//    */
//   private ResponseEntity<ApiErrorDto> handleGenericException(
//       Exception exception, HttpServletRequest request, WebRequest webRequest) {
//     ApiErrorDto apiErrorDto =
//         new ApiErrorDto(
//             "Unexpected server error occurred.",
//             exception.getMessage(),
//             request.getMethod(),
//             request.getRequestURL().toString());
//     return ResponseEntity.internalServerError().body(apiErrorDto);
//   }
// }
