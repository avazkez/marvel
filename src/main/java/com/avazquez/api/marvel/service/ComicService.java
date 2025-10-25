package com.avazquez.api.marvel.service;

import com.avazquez.api.marvel.criteria.ComicSearchCriteria;
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
   * Retrieves a paginated list of Marvel comics based on search criteria.
   *
   * <p>This method supports filtering by passing a {@link ComicSearchCriteria} object, which can
   * include:
   *
   * <ul>
   *   <li><b>characterId</b>: Character ID to filter comics that feature that character
   * </ul>
   *
   * Results are paginated using the provided {@link MyPageable} parameter.
   *
   * @param pageable Pagination information containing offset and limit
   * @param criteria Search/filter criteria for comics
   * @return List of ComicDto objects matching the criteria
   * @throws IllegalArgumentException if pageable contains invalid pagination parameters
   */
  List<ComicDto> findAll(MyPageable pageable, ComicSearchCriteria criteria);

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
