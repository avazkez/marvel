package com.avazquez.api.marvel.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Service for generating JWT tokens for authenticated users.
 * <p>Uses secret key and expiration from configuration.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Service
public class JwtService {

  /**
   * JWT expiration time in minutes, loaded from configuration.
   */
  @Value("${security.jwt.expiration-minutes}")
  private long EXPIRATION_MIN;

  /**
   * Secret key for signing JWT tokens, loaded from configuration.
   */
  @Value("${security.jwt.secret-key}")
  private String SECRET_KEY;

  /**
   * Generates a JWT token for the given user details and extra claims.
   * <p>Uses configured secret key and expiration time.
   *
   * @param userDetails the user details
   * @param extraClaims additional claims to include in the token
   * @return the generated JWT token as a String
   */
  public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {

    Date issuedAt = new Date(System.currentTimeMillis());
    Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_MIN * 60 * 1000);

    return Jwts.builder()
    .setClaims(extraClaims)
    .setSubject(userDetails.getUsername())
    .setIssuedAt(issuedAt)
    .setExpiration(expiration)
    .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
    .signWith(generateKey(), SignatureAlgorithm.HS256)
    .compact();
  }


  /**
   * Generates the signing key for JWT using the configured secret.
   *
   * @return the signing {@link Key}
   */
  private Key generateKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

