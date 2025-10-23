package com.avazquez.api.marvel.service;

import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.ComicDto;
import java.util.List;

/**
 * Service interface for Marvel comic business operations.
 *
 * <p>This service provides methods for retrieving and managing Marvel comic data, including search
 * functionality with character-based filtering and detailed comic information retrieval.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
public interface ComicService {

  /**
   * Retrieves a paginated list of Marvel comics with optional character filtering.
   *
   * <p>This method supports filtering by character ID to find comics that feature specific
   * characters. The results are returned in a paginated format based on the provided MyPageable
   * parameter.
   *
   * @param pageable Pagination information containing offset and limit
   * @param characterId Optional character ID to filter comics that feature that character (can be
   *     null for no filtering)
   * @return List of ComicDto objects matching the criteria
   * @throws IllegalArgumentException if pageable contains invalid pagination parameters
   */
  List<ComicDto> findAll(MyPageable pageable, Long characterId);

  /**
   * Retrieves detailed information for a specific Marvel comic by ID.
   *
   * <p>Returns comprehensive comic information including title, description, publication details,
   * and thumbnail image data.
   *
   * @param comicId The unique identifier of the comic to retrieve
   * @return ComicDto containing detailed comic information
   * @throws EntityNotFoundException if no comic exists with the provided ID
   * @throws IllegalArgumentException if comicId is null or negative
   */
  ComicDto findById(Long comicId);
}
