package com.avazquez.api.marvel.controller;

import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.ComicDto;
import com.avazquez.api.marvel.service.ComicService;
import com.avazquez.api.marvel.criteria.ComicSearchCriteria;
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
 * REST Controller for Marvel comic operations.
 *
 * <p>This controller provides endpoints for retrieving Marvel comic information, including
 * searching comics by various criteria and fetching individual comic details.
 *
 * <p>Base path: {@code /api/comics}
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/comics")
@Tag(name = "Comics", description = "Marvel Comics API - Retrieve comic information")
public class ComicController {

  /** Service for comic business operations. */
  @Autowired private ComicService comicService;

  /**
   * Retrieves a paginated list of Marvel comics with optional character filtering.
   *
   * <p>This endpoint supports filtering by character ID to get comics associated with specific
   * characters. Results are paginated using offset and limit parameters.
   *
   * @param characterId Optional character ID to filter comics that feature that character
   * @param offset The number of results to skip (default: 0)
   * @param limit The maximum number of results to return (default: 10)
   * @return ResponseEntity containing a list of ComicDto objects
   * @throws IllegalArgumentException if offset is negative or limit is less than 1
   */
  @Operation(
      summary = "Get all Marvel comics",
      description =
          "Retrieve a paginated list of Marvel comics with optional filtering by character ID")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved comics",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ComicDto.class))),
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
  public ResponseEntity<List<ComicDto>> findAll(
      @Parameter(description = "Character ID to filter comics that feature that character")
          @RequestParam(required = false)
          Long characterId,
      @Parameter(description = "Number of results to skip (pagination offset)")
          @RequestParam(defaultValue = "0")
          Long offset,
      @Parameter(description = "Maximum number of results to return")
          @RequestParam(defaultValue = "10")
          Long limit) {
    MyPageable pageable = new MyPageable(offset, limit);

    ComicSearchCriteria criteria = ComicSearchCriteria.builder()
        .characterId(characterId)
        .build();
    return ResponseEntity.ok(comicService.findAll(pageable, criteria));
  }

  /**
   * Retrieves detailed information for a specific Marvel comic.
   *
   * <p>Returns comic information including title, description, and thumbnail details.
   *
   * @param comicId The unique identifier of the comic to retrieve
   * @return ResponseEntity containing ComicDto with detailed comic information
   * @throws EntityNotFoundException if no comic exists with the provided ID
   */
  @Operation(
      summary = "Get comic by ID",
      description =
          "Retrieve detailed information for a specific Marvel comic by its unique identifier")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved comic",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ComicDto.class))),
        @ApiResponse(responseCode = "404", description = "Comic not found", content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid comic ID", content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content)
      })
  @GetMapping("/{comicId}")
  public ResponseEntity<ComicDto> findById(
      @Parameter(description = "Unique identifier of the comic", required = true) @PathVariable
          Long comicId) {
    return ResponseEntity.ok(comicService.findById(comicId));
  }
}
