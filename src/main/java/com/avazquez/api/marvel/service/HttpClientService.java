package com.avazquez.api.marvel.service;

import java.util.Map;
import org.springframework.http.HttpHeaders;

/**
 * Abstraction for HTTP client operations used in Marvel API integration.
 *
 * <p>Provides methods for executing HTTP GET, POST, PUT, and DELETE requests with flexible headers
 * and payloads.
 *
 * @author Alex Vazquez
 * @since 1.0
 */
public interface HttpClientService {

  /**
   * Executes an HTTP GET request.
   *
   * @param endpoint the endpoint URL
   * @param queryParams query parameters to append
   * @param headers HTTP headers to include
   * @param responseType expected response type
   * @param <T> response type
   * @return response body of type T
   */
  <T> T doGet(
      String endpoint, Map<String, String> queryParams, HttpHeaders headers, Class<T> responseType);

  /**
   * Executes an HTTP POST request.
   *
   * @param endpoint the endpoint URL
   * @param queryParams query parameters to append
   * @param headers HTTP headers to include
   * @param responseType expected response type
   * @param bodyRequest request body
   * @param <T> response type
   * @param <R> request body type
   * @return response body of type T
   */
  <T, R> T doPost(
      String endpoint,
      Map<String, String> queryParams,
      HttpHeaders headers,
      Class<T> responseType,
      R bodyRequest);

  /**
   * Executes an HTTP PUT request.
   *
   * @param endpoint the endpoint URL
   * @param queryParams query parameters to append
   * @param headers HTTP headers to include
   * @param responseType expected response type
   * @param bodyRequest request body
   * @param <T> response type
   * @param <R> request body type
   * @return response body of type T
   */
  <T, R> T doPut(
      String endpoint,
      Map<String, String> queryParams,
      HttpHeaders headers,
      Class<T> responseType,
      R bodyRequest);

  /**
   * Executes an HTTP DELETE request.
   *
   * @param endpoint the endpoint URL
   * @param queryParams query parameters to append
   * @param headers HTTP headers to include
   * @param responseType expected response type
   * @param <T> response type
   * @return response body of type T
   */
  <T> T doDelete(
      String endpoint, Map<String, String> queryParams, HttpHeaders headers, Class<T> responseType);
}
