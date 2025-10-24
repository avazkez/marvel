package com.avazquez.api.marvel.persistence.integration.marvel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Configuration class for Marvel API integration using server-side authentication.
 *
 * <p>This class implements the server-side authentication method as described in the Marvel API
 * documentation: <a href="https://developer.marvel.com/documentation/authorization">Marvel API
 * Authorization</a>.
 *
 * <p>Server-side applications must pass three parameters for authentication:
 *
 * <ul>
 *   <li><b>apikey</b> - Your Marvel API public key
 *   <li><b>ts</b> - A timestamp (or other long string which changes per request)
 *   <li><b>hash</b> - An MD5 digest of the ts parameter, private key, and public key (e.g.,
 *       md5(ts+privateKey+publicKey))
 * </ul>
 *
 * <p>Configuration Properties:
 *
 * <ul>
 *   <li>marvel.api.public-key - Marvel API public key for authentication
 *   <li>marvel.api.private-key - Marvel API private key for hash generation
 * </ul>
 *
 * <p><b>Getting API Keys:</b> To obtain Marvel API credentials, create an account at <a
 * href="https://www.marvel.com/signin?referer=https%3A%2F%2Fdeveloper.marvel.com%2Faccount">Marvel
 * Sign In</a> and access the <a href="https://developer.marvel.com/account">Marvel Developer
 * Portal</a>.
 *
 * <p><b>Security Note:</b> Private keys are kept private and not exposed via public methods,
 * following Marvel API security guidelines.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 * @see <a href="https://developer.marvel.com/documentation/authorization">Marvel API
 *     Authorization</a>
 */
@Component
@ConfigurationProperties(prefix = "marvel.api")
public class MarvelApiConfig {

  @Autowired
  @Qualifier("md5Encoder")
  private PasswordEncoder md5Encoder;

  /** Timestamp for API authentication requests. */
  private long timestamp = new Date(System.currentTimeMillis()).getTime();

  /** Marvel API public key for authentication. */
  private String publicKey;

  /** Marvel API private key for hash generation. */
  private String privateKey;

  /**
   * Generates MD5 hash for Marvel API server-side authentication.
   *
   * <p>As per Marvel API documentation, the hash is computed using the formula: <code>
   * md5(timestamp + privateKey + publicKey)</code>
   *
   * @return MD5 hash digest as hexadecimal string required by Marvel API
   * @see <a href="https://developer.marvel.com/documentation/authorization">Marvel API
   *     Authorization</a>
   */
  private String getHashKey() {
    String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);
    return md5Encoder.encode(hashDecoded);
  }

  /**
   * Creates authentication query parameters for Marvel API server-side requests.
   *
   * <p>Generates the required parameters for server-side authentication as specified in the Marvel
   * API documentation: apikey, ts (timestamp), and hash.
   *
   * @return Map containing the three required authentication parameters: ts, apikey, and hash
   * @see <a href="https://developer.marvel.com/documentation/authorization">Marvel API
   *     Authorization</a>
   */
  public Map<String, String> getAuthenticationQueryParams() {
    Map<String, String> securityParams = new HashMap<>();
    securityParams.put("ts", Long.toString(timestamp));
    securityParams.put("apikey", publicKey);
    securityParams.put("hash", getHashKey());
    return securityParams;
  }
}
