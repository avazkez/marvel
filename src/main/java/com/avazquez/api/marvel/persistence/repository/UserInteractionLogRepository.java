package com.avazquez.api.marvel.persistence.repository;

import com.avazquez.api.marvel.persistence.entity.UserInteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for user interaction logs.
 *
 * <p>Provides CRUD operations for {@link UserInteractionLog} entities.
 */
public interface UserInteractionLogRepository extends JpaRepository<UserInteractionLog, Long> {}
