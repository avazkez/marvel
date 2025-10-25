package com.avazquez.api.marvel.controller;

import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.avazquez.api.marvel.service.CharacterService;
import com.avazquez.api.marvel.criteria.CharacterSearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Marvel character operations.
 *
 * <p>This controller provides endpoints for retrieving Marvel character information, including
 * searching characters by various criteria and fetching individual character details.
 *
 * <p>Base path: {@code /api/characters}
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/characters")
@Tag(name = "Characters", description = "Marvel Characters API - Retrieve character information")
public class CharacterController {

  /** Service for character business operations. */
  @Autowired private CharacterService characterService;

  /**
   * Retrieves a paginated list of Marvel characters with optional filtering.
   *
   * <p>This endpoint supports filtering by character name, associated comics, and series. Results
   * are paginated using offset and limit parameters.
   *
   * @param name Optional character name filter (partial match supported)
   * @param comics Optional array of comic IDs to filter characters that appear in those comics
   * @param series Optional array of series IDs to filter characters that appear in those series
   * @param offset The number of results to skip (default: 0)
   * @param limit The maximum number of results to return (default: 10)
   * @return ResponseEntity containing a list of CharacterDto objects
   * @throws IllegalArgumentException if offset is negative or limit is less than 1
   */
  @Operation(
      summary = "Get all Marvel characters",
      description =
          "Retrieve a paginated list of Marvel characters with optional filtering by name, comics, and series")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved characters",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CharacterDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request parameters",
            content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content)
      })
  @GetMapping
  public ResponseEntity<List<CharacterDto>> findAll(
      @Parameter(description = "Character name filter (partial match)")
          @RequestParam(required = false)
          String name,
      @Parameter(description = "Array of comic IDs to filter characters")
          @RequestParam(required = false)
          int[] comics,
      @Parameter(description = "Array of series IDs to filter characters")
          @RequestParam(required = false)
          int[] series,
      @Parameter(description = "Number of results to skip (pagination offset)")
          @RequestParam(defaultValue = "0")
          long offset,
      @Parameter(description = "Maximum number of results to return")
          @RequestParam(defaultValue = "10")
          long limit) {
    MyPageable pageable = new MyPageable(offset, limit);
    CharacterSearchCriteria criteria = CharacterSearchCriteria.builder()
        .name(name)
        .comics(comics)
        .series(series)
        .build();
    return ResponseEntity.ok(characterService.findAll(pageable, criteria));
  }

  /**
   * Retrieves detailed information for a specific Marvel character.
   *
   * <p>Returns character information including additional details not available in the list
   * endpoint.
   *
   * @param characterId The unique identifier of the character to retrieve
   * @return ResponseEntity containing CharacterInfoDto with detailed character information
   * @throws EntityNotFoundException if no character exists with the provided ID
   */
  @Operation(
      summary = "Get character by ID",
      description =
          "Retrieve detailed information for a specific Marvel character by their unique identifier")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved character",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CharacterDto.CharacterInfoDto.class))),
        @ApiResponse(responseCode = "404", description = "Character not found", content = @Content),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid character ID",
            content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content)
      })
  @GetMapping("/{characterId}")
  public ResponseEntity<CharacterDto.CharacterInfoDto> findCharacterById(
      @Parameter(description = "Unique identifier of the character", required = true) @PathVariable
          Long characterId) {
    return ResponseEntity.ok(characterService.findInfoById(characterId));
  }
}
