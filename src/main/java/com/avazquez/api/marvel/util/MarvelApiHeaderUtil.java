package com.avazquez.api.marvel.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Utility class for Marvel API HTTP header construction. Centralizes default header logic for all
 * Marvel API requests.
 */
public final class MarvelApiHeaderUtil {

  private MarvelApiHeaderUtil() {
    // Utility class; prevent instantiation
  }

  /**
   * Returns default HTTP headers for Marvel API requests.
   *
   * @return HttpHeaders with Content-Type set to application/json
   */
  public static HttpHeaders getDefaultHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
}
