package com.avazquez.api.marvel.dto.exception;

/**
 * DTO for representing structured API error responses.
 *
 * <p>Encapsulates error details returned by the API, including a user-facing message, backend
 * exception message, HTTP method, and request URL. Used for consistent error formatting in global
 * exception handling and security handlers.
 *
 * @param message Human-readable error message for clients
 * @param backendMessage Detailed backend exception message (for debugging)
 * @param method HTTP method of the request (e.g., GET, POST)
 * @param url Request URL that caused the error
 */
public record ApiErrorDto(String message, String backendMessage, String method, String url) {}
