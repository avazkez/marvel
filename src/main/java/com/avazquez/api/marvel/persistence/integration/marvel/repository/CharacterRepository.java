package com.avazquez.api.marvel.persistence.integration.marvel.repository;

import com.avazquez.api.marvel.criteria.CharacterSearchCriteria;
import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.MarvelApiConfig;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.avazquez.api.marvel.persistence.integration.marvel.mapper.CharacterMapper;
import com.avazquez.api.marvel.persistence.integration.marvel.mapper.MarvelQueryParameterMapper;
import com.avazquez.api.marvel.service.HttpClientService;
import com.avazquez.api.marvel.util.MarvelApiHeaderUtil;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Repository for Marvel character data access operations.
 *
 * <p>This repository handles data retrieval operations for Marvel characters, including integration
 * with external Marvel API and local data caching. It provides methods for querying character
 * information with various filtering and pagination options.
 *
 * <p>Data Sources:
 *
 * <ul>
 *   <li>Marvel API - Primary source for character data
 *   <li>Local cache - For performance optimization
 *   <li>Database - For persistent storage and offline capability
 * </ul>
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
/**
 * Repository for Marvel character data access operations.
 *
 * <p>Handles integration with the Marvel API and local data sources for character queries. Provides
 * filtering, pagination, and retrieval methods for character data. <b>Dependencies:</b>
 *
 * <ul>
 *   <li>{@link MarvelApiConfig} for authentication and API configuration
 *   <li>Injected base path for API endpoint construction
 * </ul>
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Repository
public class CharacterRepository {
  /** Marvel API configuration for authentication and endpoint parameters. */
  @Autowired private MarvelApiConfig marvelApiConfig;

  /**
   * HTTP client service abstraction for making external Marvel API calls. Injected by Spring for
   * loose coupling and testability.
   */
  @Autowired private HttpClientService httpClientService;

  /** Base path for Marvel API endpoints, injected from application properties. */
  @Value("${integration.marvel.base-path}")
  private String basePath;

  /** Full path for character endpoint, constructed after bean initialization. */
  private String characterPath;

  /** Initializes the character API endpoint path after bean construction. */
  @PostConstruct
  private void setPath() {
    characterPath = basePath.concat("/").concat("characters");
  }

  /**
   * Retrieves a paginated list of Marvel characters with optional filtering.
   *
   * @param pageable pagination parameters (offset and limit)
   * @param criteria encapsulates all character search/filter parameters (name, comics, series,
   *     etc.)
   * @return list of characters matching the criteria
   */
  public List<CharacterDto> findAll(MyPageable pageable, CharacterSearchCriteria criteria) {
    Map<String, String> marvelQueryParams =
        MarvelQueryParameterMapper.mapCharacterCriteria(pageable, criteria, marvelApiConfig);
    JsonNode response =
        httpClientService.doGet(
            characterPath,
            marvelQueryParams,
            MarvelApiHeaderUtil.getDefaultHeaders(),
            JsonNode.class);
    return CharacterMapper.toDtoList(response);
  }

  /**
   * Retrieves detailed character information by ID.
   *
   * @param characterId the unique character identifier
   * @return character information DTO or null if not found
   */
  public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
    Map<String, String> marvelQueryParams = marvelApiConfig.getAuthenticationQueryParams();
    String finalUrl = characterPath.concat("/").concat(Long.toString(characterId));
    JsonNode response =
        httpClientService.doGet(
            finalUrl, marvelQueryParams, MarvelApiHeaderUtil.getDefaultHeaders(), JsonNode.class);
    return CharacterMapper.toInfoDtoList(response).get(0);
  }
}
