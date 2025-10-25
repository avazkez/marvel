package com.avazquez.api.marvel.persistence.integration.marvel.mapper;

import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.ThumbnailDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for converting Marvel API character JSON responses to CharacterDto objects.
 *
 * @author Alex Vazquez
 * @since 1.0
 */
public class CharacterMapper {

  /**
   * Converts the root JSON node to a list of CharacterDto objects.
   *
   * @param rootNode the root JSON node from the Marvel API response
   * @return a list of CharacterDto objects, or an empty list if none found
   * @throws IllegalArgumentException if rootNode is null
   */
  public static List<CharacterDto> toDtoList(JsonNode rootNode) {
    ArrayNode resultsNode = getResultsNode(rootNode);
    List<CharacterDto> charactersList = new ArrayList<>();
    resultsNode
        .elements()
        .forEachRemaining(
            node -> {
              charactersList.add(CharacterMapper.toDto(node));
            });
    return charactersList;
  }

  /**
   * Extracts the "results" ArrayNode from the root JSON node.
   *
   * @param rootNode the root JSON node
   * @return the ArrayNode containing character results
   * @throws IllegalArgumentException if rootNode is null
   */
  private static ArrayNode getResultsNode(JsonNode rootNode) {
    if (rootNode == null) {
      throw new IllegalArgumentException("Root JSON node cannot be null");
    }
    JsonNode dataNode = rootNode.get("data");
    return (ArrayNode) dataNode.get("results");
  }

  /**
   * Converts a single character JSON node to a CharacterDto object.
   *
   * @param characterNode the JSON node representing a character
   * @return a CharacterDto object
   * @throws IllegalArgumentException if characterNode is null
   */
  private static CharacterDto toDto(JsonNode characterNode) {
    if (characterNode == null) {
      throw new IllegalArgumentException("Character JSON node cannot be null");
    }
    JsonNode idNode = characterNode.get("id");
    JsonNode nameNode = characterNode.get("name");
    JsonNode descNode = characterNode.get("description");
    JsonNode modNode = characterNode.get("modified");
    JsonNode uriNode = characterNode.get("resourceURI");

    long id = idNode != null ? idNode.asLong(-1) : -1;
    String name = nameNode != null ? nameNode.asText("") : "";
    String description = descNode != null ? descNode.asText("") : "";
    String modified = modNode != null ? modNode.asText("") : "";
    String resourceURI = uriNode != null ? uriNode.asText("") : "";
    return new CharacterDto(id, name, description, modified, resourceURI);
  }

  /**
   * Converts the root JSON node to a list of CharacterInfoDto objects.
   *
   * @param rootNode the root JSON node from the Marvel API response
   * @return a list of CharacterInfoDto objects, or an empty list if none found
   * @throws IllegalArgumentException if rootNode is null
   */
  public static List<CharacterDto.CharacterInfoDto> toInfoDtoList(JsonNode rootNode) {
    ArrayNode resultsNode = getResultsNode(rootNode);
    List<CharacterDto.CharacterInfoDto> characterInfoList = new ArrayList<>();
    resultsNode
        .elements()
        .forEachRemaining(
            node -> {
              characterInfoList.add(CharacterMapper.toInfoDto(node));
            });
    return characterInfoList;
  }

  /**
   * Converts a single character JSON node to a CharacterInfoDto object, including image info.
   *
   * @param characterNode the JSON node representing a character
   * @return a CharacterInfoDto object
   * @throws IllegalArgumentException if characterNode is null
   */
  private static CharacterDto.CharacterInfoDto toInfoDto(JsonNode characterNode) {
    if (characterNode == null) {
      throw new IllegalArgumentException("Character JSON node cannot be null");
    }
    JsonNode thumbnailNode = characterNode.get("thumbnail");
    ThumbnailDto thumbnailDto = ThumbnailMapper.toDto(thumbnailNode);
    String image = thumbnailDto != null ? thumbnailDto.path().concat(".").concat(thumbnailDto.extension()) : "";
    JsonNode descNode = characterNode.get("description");
    String description = descNode != null ? descNode.asText("") : "";
    return new CharacterDto.CharacterInfoDto(image, description);
  }
}
