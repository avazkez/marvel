package com.avazquez.api.marvel.persistence.integration.marvel.mapper;

import com.avazquez.api.marvel.persistence.integration.marvel.dto.ThumbnailDto;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Mapper for converting Marvel API thumbnail JSON nodes to ThumbnailDto objects.
 *
 * @author Alex Vazquez
 * @since 1.0
 */
public class ThumbnailMapper {

  /**
   * Converts a thumbnail JSON node to a ThumbnailDto object.
   *
   * @param thumbnailNode the JSON node representing a thumbnail
   * @return a ThumbnailDto object
   * @throws IllegalArgumentException if thumbnailNode is null
   */
  public static ThumbnailDto toDto(JsonNode thumbnailNode) {
    if (thumbnailNode == null) {
      throw new IllegalArgumentException("Thumbnail JSON node cannot be null");
    }
    return new ThumbnailDto(
        thumbnailNode.get("path").asText(), thumbnailNode.get("extension").asText());
  }
}
