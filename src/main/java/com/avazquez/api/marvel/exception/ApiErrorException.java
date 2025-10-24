package com.avazquez.api.marvel.exception;

/**
 * Exception thrown when an error occurs during an API call to an external service.
 *
 * <p>Used to signal HTTP or integration errors in the Marvel API integration layer.
 *
 * @author Alex Vazquez
 * @since 1.0
 */
public class ApiErrorException extends RuntimeException {

  /** Constructs a new ApiErrorException with {@code null} as its detail message. */
  public ApiErrorException() {
    super();
  }

  /**
   * Constructs a new ApiErrorException with the specified detail message.
   *
   * @param message the detail message
   */
  public ApiErrorException(String message) {
    super(message);
  }

  /**
   * Constructs a new ApiErrorException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of the exception
   */
  public ApiErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new ApiErrorException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public ApiErrorException(Throwable cause) {
    super(cause);
  }
}
