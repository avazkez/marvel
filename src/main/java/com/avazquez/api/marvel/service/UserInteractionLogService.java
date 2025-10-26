package com.avazquez.api.marvel.service;

import com.avazquez.api.marvel.dto.GetUserInteractionLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Service interface for user interaction log operations. */
public interface UserInteractionLogService {

  /**
   * Retrieves all user interaction logs with pagination.
   *
   * @param pageable pagination information
   * @return a page of user interaction log DTOs
   */
  Page<GetUserInteractionLogDto> findAll(Pageable pageable);

  /**
   * Retrieves user interaction logs filtered by username with pagination.
   *
   * @param pageable pagination information
   * @param username the username to filter logs
   * @return a page of user interaction log DTOs for the given username
   */
  Page<GetUserInteractionLogDto> findByUsername(Pageable pageable, String username);
}
