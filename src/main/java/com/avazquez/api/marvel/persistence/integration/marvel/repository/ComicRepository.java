package com.avazquez.api.marvel.persistence.integration.marvel.repository;

import com.avazquez.api.marvel.criteria.ComicSearchCriteria;
import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.MarvelApiConfig;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.ComicDto;
import com.avazquez.api.marvel.persistence.integration.marvel.mapper.ComicMapper;
import com.avazquez.api.marvel.persistence.integration.marvel.mapper.ComicQueryParameterMapper;
import com.avazquez.api.marvel.service.HttpClientService;
import com.avazquez.api.marvel.util.MarvelApiHeaderUtil;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Repository for Marvel comic data access operations.
 *
 * <p>This repository handles data retrieval operations for Marvel comics, including integration
 * with external Marvel API and local data caching. It provides methods for querying comic
 * information with various filtering and pagination options.
 *
 * <p>Data Sources:
 *
 * <ul>
 *   <li>Marvel API - Primary source for comic data
 *   <li>Local cache - For performance optimization
 *   <li>Database - For persistent storage and offline capability
 * </ul>
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Repository
public class ComicRepository {

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

  /** Full path for comic endpoint, constructed after bean initialization. */
  private String comicPath;

  /** Initializes the comic API endpoint path after bean construction. */
  @PostConstruct
  private void setPath() {
    comicPath = basePath.concat("/").concat("comics");
  }

  /**
   * Retrieves a paginated list of Marvel comics with optional filtering.
   *
   * @param pageable Pagination parameters containing offset and limit for result set
   * @param criteria ComicSearchCriteria encapsulating all comic search/filter parameters
   * @return List of ComicDto objects matching the criteria; empty list if no results found
   */
  public List<ComicDto> findAll(MyPageable pageable, ComicSearchCriteria criteria) {
    Map<String, String> marvelQueryParams = marvelApiConfig.getAuthenticationQueryParams();

    marvelQueryParams.putAll(ComicQueryParameterMapper.mapComicCriteria(pageable, criteria));
    JsonNode response =
        httpClientService.doGet(
            comicPath, marvelQueryParams, MarvelApiHeaderUtil.getDefaultHeaders(), JsonNode.class);

    return ComicMapper.toDtoList(response);
  }

  /**
   * Retrieves detailed information for a specific Marvel comic by its unique identifier.
   *
   * <p>This method fetches comprehensive comic data including title, description, publication
   * details, and thumbnail information. It first checks local cache before querying the Marvel API.
   *
   * <p>Implementation Details:
   *
   * <ul>
   *   <li>Checks local cache for existing comic data
   *   <li>Queries Marvel API if cache miss occurs
   *   <li>Validates comic exists and is accessible
   *   <li>Transforms API response to ComicDto
   *   <li>Updates cache with retrieved data
   * </ul>
   *
   * @param comicId The unique identifier of the comic to retrieve; must be positive
   * @return ComicDto containing detailed comic information
   * @throws EntityNotFoundException if no comic exists with the specified ID
   * @throws IllegalArgumentException if comicId is null or negative
   * @throws RuntimeException if Marvel API returns an error or is unreachable
   */
  public ComicDto findById(Long comicId) {
    Map<String, String> marvelQueryParams = marvelApiConfig.getAuthenticationQueryParams();

    String finalUrl = comicPath.concat("/").concat(Long.toString(comicId));
    JsonNode response =
        httpClientService.doGet(
            finalUrl, marvelQueryParams, MarvelApiHeaderUtil.getDefaultHeaders(), JsonNode.class);

    return ComicMapper.toDtoList(response).get(0);
  }
}
