package com.avazquez.api.marvel.mapper;

import com.avazquez.api.marvel.dto.GetUserInteractionLogDto;
import com.avazquez.api.marvel.persistence.entity.UserInteractionLog;

/**
 * Mapper for converting {@link UserInteractionLog} entities to {@link GetUserInteractionLogDto}
 * DTOs.
 *
 * <p>Provides static mapping methods for user interaction log data transfer objects.
 */
public class UserInteractionLogMapper {

  /**
   * Converts a {@link UserInteractionLog} entity to a {@link GetUserInteractionLogDto}.
   *
   * @param entity the user interaction log entity to convert
   * @return the corresponding DTO, or {@code null} if the entity is {@code null}
   */
  public static GetUserInteractionLogDto toDto(UserInteractionLog entity) {
    if (entity == null) {
      return null;
    }
    return new GetUserInteractionLogDto(
        entity.getId(),
        entity.getUrl(),
        entity.getHttpMethod(),
        entity.getUsername(),
        entity.getTimestamp(),
        entity.getRemoteAddress());
  }
}
