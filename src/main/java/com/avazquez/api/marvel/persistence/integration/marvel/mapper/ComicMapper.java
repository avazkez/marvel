package com.avazquez.api.marvel.persistence.integration.marvel.mapper;

import com.avazquez.api.marvel.persistence.integration.marvel.dto.ComicDto;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.ThumbnailDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for converting Marvel API comic JSON responses to ComicDto objects.
 *
 * @author Alex Vazquez
 * @since 1.0
 */
public class ComicMapper {
  /**
   * Converts the root JSON node to a list of ComicDto objects.
   *
   * @param rootNode the root JSON node from the Marvel API response
   * @return a list of ComicDto objects, or an empty list if none found
   * @throws IllegalArgumentException if rootNode is null
   */
  public static List<ComicDto> toDtoList(JsonNode rootNode) {
    ArrayNode resultsNode = getResultsNode(rootNode);
    List<ComicDto> comicList = new ArrayList<>();
    resultsNode
        .elements()
        .forEachRemaining(
            node -> {
              comicList.add(ComicMapper.toDto(node));
            });
    return comicList;
  }

  /**
   * Extracts the "results" ArrayNode from the root JSON node.
   *
   * @param rootNode the root JSON node
   * @return the ArrayNode containing comic results
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
   * Converts a single comic JSON node to a ComicDto object using safe defaults.
   *
   * @param comicNode the JSON node representing a comic
   * @return a ComicDto object (fields may be empty if missing in JSON)
   * @throws IllegalArgumentException if comicNode is null
   */
  private static ComicDto toDto(JsonNode comicNode) {
    if (comicNode == null) {
      throw new IllegalArgumentException("Comic JSON node cannot be null");
    }

  JsonNode idNode = comicNode.get("id");
  JsonNode titleNode = comicNode.get("title");
  JsonNode descNode = comicNode.get("description");
  JsonNode modNode = comicNode.get("modified");
  JsonNode uriNode = comicNode.get("resourceUri");
  JsonNode thumbNode = comicNode.get("thumbnail");

  ThumbnailDto thumbnailDto = ThumbnailMapper.toDto(thumbNode);

  ComicDto comicDto =
    new ComicDto(
      idNode != null ? idNode.asLong(-1) : -1,
      titleNode != null ? titleNode.asText("") : "",
      descNode != null ? descNode.asText("") : "",
      modNode != null ? modNode.asText("") : "",
      uriNode != null ? uriNode.asText("") : "",
      thumbnailDto);

  return comicDto;
  }
}
