package com.avazquez.api.marvel.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO for login request containing username and password.
 *
 * <p>After receiving a JWT token in the response, you can decode its header and payload using <a
 * href="https://jwt.io/" target="_blank">jwt.io</a>. Paste the token at https://jwt.io/ to inspect
 * its claims and header information.
 *
 * @param username the username (not blank)
 * @param password the password (not blank)
 */
public record LoginRequest(
    @NotBlank
        @Schema(
            example = "ironman",
            description = "Username for authentication. Try 'ironman' or 'thor'.")
        String username,
    @NotBlank
        @Schema(
            example = "Friday1234",
            description = "Password for authentication. Try 'Friday1234' or 'Mjolnir5678'.")
        String password) {}
