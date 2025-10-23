package com.avazquez.api.marvel.service;

import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;
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
   * Retrieves a paginated list of Marvel characters with optional filtering criteria.
   *
   * <p>This method supports filtering by character name, associated comics, and series. The results
   * are returned in a paginated format based on the provided MyPageable parameter.
   *
   * @param pageable Pagination information containing offset and limit
   * @param name Optional character name filter (can be null for no filtering)
   * @param comics Optional array of comic IDs to filter characters (can be null)
   * @param series Optional array of series IDs to filter characters (can be null)
   * @return List of CharacterDto objects matching the criteria
   * @throws IllegalArgumentException if pageable contains invalid pagination parameters
   */
  List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series);

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
