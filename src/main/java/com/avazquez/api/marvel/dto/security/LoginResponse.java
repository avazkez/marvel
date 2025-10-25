package com.avazquez.api.marvel.dto.security;

/**
 * DTO for login response containing the JWT token.
 *
 * @param jwt the generated JWT token
 */
public record LoginResponse(String jwt) {}
