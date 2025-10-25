package com.avazquez.api.marvel.dto.security;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for login request containing username and password.
 *
 * @param username the username (not blank)
 * @param password the password (not blank)
 */
public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
