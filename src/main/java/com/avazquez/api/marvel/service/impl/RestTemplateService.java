package com.avazquez.api.marvel.service.impl;

import com.avazquez.api.marvel.exception.ApiErrorException;
import com.avazquez.api.marvel.service.HttpClientService;
import java.net.URI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Implementation of HttpClientService using Spring's RestTemplate for HTTP operations. Handles GET,
 * POST, PUT, and DELETE requests with error handling and flexible headers.
 *
 * @author Alex Vazquez
 * @since 1.0
 */
@Service
public class RestTemplateService implements HttpClientService {

  /**
   * Spring RestTemplate used for executing HTTP requests. Injected by Spring container.
   */
  @Autowired private RestTemplate restTemplate;

  /** {@inheritDoc} */
  @Override
  public <T> T doGet(
      String endpoint,
      Map<String, String> queryParams,
      HttpHeaders headers,
      Class<T> responseType) {
    return executeRequest(endpoint, queryParams, headers, null, responseType, HttpMethod.GET);
  }

  /** {@inheritDoc} */
  @Override
  public <T, R> T doPost(
      String endpoint,
      Map<String, String> queryParams,
      HttpHeaders headers,
      Class<T> responseType,
      R bodyRequest) {
    return executeRequest(
        endpoint, queryParams, headers, bodyRequest, responseType, HttpMethod.POST);
  }

  /** {@inheritDoc} */
  @Override
  public <T, R> T doPut(
      String endpoint,
      Map<String, String> queryParams,
      HttpHeaders headers,
      Class<T> responseType,
      R bodyRequest) {
    return executeRequest(
        endpoint, queryParams, headers, bodyRequest, responseType, HttpMethod.PUT);
  }

  /** {@inheritDoc} */
  @Override
  public <T> T doDelete(
      String endpoint,
      Map<String, String> queryParams,
      HttpHeaders headers,
      Class<T> responseType) {
    return executeRequest(endpoint, queryParams, headers, null, responseType, HttpMethod.DELETE);
  }

  /**
   * Executes an HTTP request with the given parameters and handles errors.
   *
   * @param endpoint the endpoint URL
   * @param queryParams query parameters to append
   * @param headers HTTP headers to include
   * @param bodyRequest request body (nullable)
   * @param responseType expected response type
   * @param method HTTP method to use
   * @param <T> response type
   * @param <R> request body type
   * @return response body of type T
   * @throws ApiErrorException if the response status is not successful
   */
  private <T, R> T executeRequest(
      String endpoint,
      Map<String, String> queryParams,
      HttpHeaders headers,
      R bodyRequest,
      Class<T> responseType,
      HttpMethod method) {
    String url = buildUrl(endpoint, queryParams);
    HttpEntity<?> httpEntity =
        (bodyRequest != null) ? new HttpEntity<>(bodyRequest, headers) : new HttpEntity<>(headers);
    ResponseEntity<T> response = restTemplate.exchange(url, method, httpEntity, responseType);
    handleErrorResponse(response, method, url);
    return response.getBody();
  }

  /**
   * Handles error responses and throws ApiErrorException if not successful.
   *
   * @param response the HTTP response entity
   * @param method the HTTP method used
   * @param url the request URL
   * @param <T> response type
   * @throws ApiErrorException if the response status is not successful
   */
  private <T> void handleErrorResponse(ResponseEntity<T> response, HttpMethod method, String url) {
    int status = response.getStatusCode().value();
    boolean isSuccess =
        (method == HttpMethod.POST)
            ? (status == HttpStatus.OK.value() || status == HttpStatus.CREATED.value())
            : (status == HttpStatus.OK.value());
    if (!isSuccess) {
      String message =
          String.format("Error during %s request to %s - Status code: %d", method, url, status);
      throw new ApiErrorException(message);
    }
  }

  /**
   * Builds a URL with the given endpoint and query parameters.
   *
   * @param endpoint the endpoint URL
   * @param queryParams query parameters to append
   * @return the full URL as a string
   */
  private static String buildUrl(String endpoint, Map<String, String> queryParams) {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance().uri(URI.create(endpoint));
    if (queryParams != null) {
      for (Map.Entry<String, String> entry : queryParams.entrySet()) {
        uriBuilder.queryParam(entry.getKey(), entry.getValue());
      }
    }
    return uriBuilder.build().toUriString();
  }
}
