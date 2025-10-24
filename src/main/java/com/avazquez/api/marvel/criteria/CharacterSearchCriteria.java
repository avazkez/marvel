package com.avazquez.api.marvel.criteria;

import lombok.Builder;
import lombok.Getter;

/**
 * Encapsulates search/filter criteria for Marvel character queries.
 *
 * <p>This class is designed for extensibility and can be expanded with additional fields as needed.
 * Example usage:
 *
 * <pre>
 *   CharacterSearchCriteria criteria = CharacterSearchCriteria.builder()
 *       .name("Spider-Man")
 *       .comics(new int[]{123, 456})
 *       .series(new int[]{789})
 *       .build();
 * </pre>
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Getter
@Builder
public class CharacterSearchCriteria {
  private final String name;
  private final int[] comics;
  private final int[] series;
}
