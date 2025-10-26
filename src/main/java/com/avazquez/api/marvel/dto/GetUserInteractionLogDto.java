package com.avazquez.api.marvel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * DTO for user interaction log data.
 *
 * <p>Contains details about a user's interaction with the system.
 */
public record GetUserInteractionLogDto(
    Long id,
    String url,
    String httpMethod,
    String username,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") LocalDateTime timestamp,
    String remoteAddress) {}
