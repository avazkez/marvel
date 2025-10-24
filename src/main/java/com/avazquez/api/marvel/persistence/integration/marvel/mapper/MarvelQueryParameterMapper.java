package com.avazquez.api.marvel.persistence.integration.marvel.mapper;

import com.avazquez.api.marvel.criteria.CharacterSearchCriteria;
import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.MarvelApiConfig;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.StringUtils;

/**
 * Utility class for mapping character search criteria and pagination information to Marvel API
 * query parameters.
 *
 * <p>This class centralizes the logic for transforming domain-specific filter and pagination
 * objects into a map of query parameters suitable for external API calls. It is designed for reuse
 * and maintainability as the set of supported filters grows. <b>Layer Responsibility:</b>
 *
 * <ul>
 *   <li>Does <i>not</i> handle authentication or configuration concerns directly.
 *   <li>Focuses solely on mapping domain criteria to query parameters.
 * </ul>
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
public class MarvelQueryParameterMapper {

  /**
   * Maps {@link CharacterSearchCriteria} and {@link MyPageable} to Marvel API query parameters.
   *
   * <p>This method transforms the provided search criteria and pagination information into a map of
   * query parameters. It expects the caller to provide authentication parameters (typically from
   * {@link MarvelApiConfig#getAuthenticationQueryParams()}) and merges them with filter and
   * pagination parameters.
   *
   * @param pageable pagination information (offset, limit)
   * @param criteria character search/filter criteria (name, comics, series, etc.)
   * @param marvelApiConfig Marvel API configuration for authentication query params
   * @return map of query parameters for Marvel API requests
   */
  public static Map<String, String> mapCharacterCriteria(
      MyPageable pageable, CharacterSearchCriteria criteria, MarvelApiConfig marvelApiConfig) {
    Map<String, String> params = new HashMap<>(marvelApiConfig.getAuthenticationQueryParams());
    params.put("offset", Long.toString(pageable.offset()));
    params.put("limit", Long.toString(pageable.limit()));

    if (StringUtils.hasText(criteria.getName())) {
      params.put("name", criteria.getName());
    }
    if (criteria.getComics() != null) {
      params.put("comics", joinIntArray(criteria.getComics()));
    }
    if (criteria.getSeries() != null) {
      params.put("series", joinIntArray(criteria.getSeries()));
    }
    return params;
  }

  /**
   * Joins an array of integers into a comma-separated string for query parameters.
   *
   * @param array the array of integers to join
   * @return a comma-separated string representation of the array, or an empty string if null/empty
   */
  private static String joinIntArray(int[] array) {
    if (array == null || array.length == 0) return "";
    StringBuilder sb = new StringBuilder();
    for (int i : array) {
      if (sb.length() > 0) sb.append(",");
      sb.append(i);
    }
    return sb.toString();
  }
}
