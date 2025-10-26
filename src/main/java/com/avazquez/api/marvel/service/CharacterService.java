package com.avazquez.api.marvel.service;

import com.avazquez.api.marvel.criteria.CharacterSearchCriteria;
import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service interface for Marvel character business operations.
 *
 * <p>This service provides methods for retrieving and managing Marvel character data, including
 * search functionality and detailed character information retrieval.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
public interface CharacterService {

  /**
   * Retrieves a paginated list of Marvel characters based on search criteria.
   *
   * <p>This method supports filtering by passing a {@link CharacterSearchCriteria} object, which
   * can include:
   *
   * <ul>
   *   <li><b>name</b>: Character name filter (partial match)
   *   <li><b>comics</b>: Array of comic IDs to filter characters
   *   <li><b>series</b>: Array of series IDs to filter characters
   * </ul>
   *
   * <p>Results are paginated using the provided {@link MyPageable} parameter.
   *
   * @param pageable Pagination information containing offset and limit
   * @param criteria Search/filter criteria for characters
   * @return List of CharacterDto objects matching the criteria
   * @throws IllegalArgumentException if pageable contains invalid pagination parameters
   */
  List<CharacterDto> findAll(
      MyPageable pageable, com.avazquez.api.marvel.criteria.CharacterSearchCriteria criteria);

  /**
   * Retrieves detailed information for a specific Marvel character by ID.
   *
   * <p>Returns comprehensive character information including additional details not available in
   * the list view.
   *
   * @param characterId The unique identifier of the character to retrieve
   * @return CharacterInfoDto containing detailed character information
   * @throws EntityNotFoundException if no character exists with the provided ID
   * @throws IllegalArgumentException if characterId is null or negative
   */
  CharacterDto.CharacterInfoDto findInfoById(Long characterId);
}
