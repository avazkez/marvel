package com.avazquez.api.marvel.persistence.repository;

import com.avazquez.api.marvel.persistence.entity.UserInteractionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for user interaction logs.
 *
 * <p>Provides CRUD operations for {@link UserInteractionLog} entities.
 */
public interface UserInteractionLogRepository extends JpaRepository<UserInteractionLog, Long> {

  /**
   * Finds user interaction logs by username with pagination.
   *
   * @param pageable pagination information
   * @param username the username to filter logs
   * @return a page of user interaction logs for the given username
   */
  Page<UserInteractionLog> findByUsername(Pageable pageable, String username);
}
