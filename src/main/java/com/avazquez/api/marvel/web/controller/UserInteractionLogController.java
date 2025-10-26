package com.avazquez.api.marvel.web.controller;

import com.avazquez.api.marvel.dto.GetUserInteractionLogDto;
import com.avazquez.api.marvel.service.UserInteractionLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for user interaction logs.
 *
 * <p>Provides endpoints to retrieve user interaction logs.
 */
@RestController
@RequestMapping("/user-interaction-logs")
@Tag(name = "User Interaction Logs", description = "Operations related to user interaction logs")
public class UserInteractionLogController {

  /** Service for user interaction log operations. */
  @Autowired private UserInteractionLogService userInteractionLogService;

  /**
   * Retrieves all user interaction logs with pagination.
   *
   * @param offset the offset for pagination
   * @param limit the maximum number of records per page
   * @return a page of user interaction log DTOs
   */
  @Operation(
      summary = "Get all user interaction logs",
      description = "Returns a paginated list of all user interaction logs.")
  @GetMapping
  public ResponseEntity<Page<GetUserInteractionLogDto>> findAll(
      @Parameter(description = "The offset for pagination", example = "0")
          @RequestParam(defaultValue = "0")
          int offset,
      @Parameter(description = "The maximum number of records per page", example = "10")
          @RequestParam(defaultValue = "10")
          int limit) {

    Pageable pageable = buildPageable(offset, limit);
    return ResponseEntity.ok(userInteractionLogService.findAll(pageable));
  }

  /**
   * Builds a Pageable object from offset and limit.
   *
   * @param offset the offset for pagination
   * @param limit the maximum number of records per page
   * @return a Pageable object
   */
  private static Pageable buildPageable(int offset, int limit) {
    Pageable pageable;

    if (offset < 0) {
      throw new IllegalArgumentException("The offset attribute cannot be smaller than zero");
    }

    if (limit <= 0) {
      throw new IllegalArgumentException(
          "The limit attribute cannot be less than or equal to zero");
    }

    if (offset == 0) {
      pageable = PageRequest.of(0, limit);
    } else {
      pageable = PageRequest.of(offset / limit, limit);
    }

    return pageable;
  }

  /**
   * Retrieves user interaction logs filtered by username with pagination.
   *
   * @param username the username to filter logs
   * @param offset the offset for pagination
   * @param limit the maximum number of records per page
   * @return a page of user interaction log DTOs for the given username
   */
  @Operation(
      summary = "Get user interaction logs by username",
      description = "Returns a paginated list of user interaction logs for a specific username.")
  @GetMapping("/{username}")
  public ResponseEntity<Page<GetUserInteractionLogDto>> findByUsername(
      @Parameter(description = "The username to filter logs", example = "johndoe") @PathVariable
          String username,
      @Parameter(description = "The offset for pagination", example = "0")
          @RequestParam(defaultValue = "0")
          int offset,
      @Parameter(description = "The maximum number of records per page", example = "10")
          @RequestParam(defaultValue = "10")
          int limit) {

    Pageable pageable = buildPageable(offset, limit);
    return ResponseEntity.ok(userInteractionLogService.findByUsername(pageable, username));
  }
}
