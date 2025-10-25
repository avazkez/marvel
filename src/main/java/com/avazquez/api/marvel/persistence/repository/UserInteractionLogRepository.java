package com.avazquez.api.marvel.persistence.repository;

import com.avazquez.api.marvel.persistence.entity.UserInteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInteractionLogRepository extends JpaRepository<UserInteractionLog, Long> {}
