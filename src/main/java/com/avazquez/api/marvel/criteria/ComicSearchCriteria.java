package com.avazquez.api.marvel.criteria;

import lombok.Builder;
import lombok.Getter;

/**
 * Encapsulates search/filter criteria for Marvel comic queries.
 *
 * <p>Designed for extensibility; add more fields as new filters are supported.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Getter
@Builder
public class ComicSearchCriteria {
  /** Optional character ID to filter comics featuring that character. */
  private final Long characterId;
}
