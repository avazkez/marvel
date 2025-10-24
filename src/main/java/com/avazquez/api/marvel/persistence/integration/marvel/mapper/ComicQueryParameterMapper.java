package com.avazquez.api.marvel.persistence.integration.marvel.mapper;

import com.avazquez.api.marvel.criteria.ComicSearchCriteria;
import com.avazquez.api.marvel.dto.MyPageable;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for mapping comic search criteria and pagination to Marvel API query parameters.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
public class ComicQueryParameterMapper {
  /**
   * Maps {@link ComicSearchCriteria} and {@link MyPageable} to Marvel API query parameters.
   *
   * @param pageable pagination information (offset, limit)
   * @param criteria comic search/filter criteria (characterId, etc.)
   * @return map of query parameters for Marvel API requests
   */
  public static Map<String, String> mapComicCriteria(
      MyPageable pageable, ComicSearchCriteria criteria) {
    Map<String, String> params = new HashMap<>();
    params.put("offset", Long.toString(pageable.offset()));
    params.put("limit", Long.toString(pageable.limit()));
    if (criteria != null && criteria.getCharacterId() != null && criteria.getCharacterId() > 0) {
      params.put("characters", Long.toString(criteria.getCharacterId()));
    }
    return params;
  }
}
