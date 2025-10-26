package com.avazquez.api.marvel.service.impl;

import com.avazquez.api.marvel.dto.GetUserInteractionLogDto;
import com.avazquez.api.marvel.mapper.UserInteractionLogMapper;
import com.avazquez.api.marvel.persistence.repository.UserInteractionLogRepository;
import com.avazquez.api.marvel.service.UserInteractionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** Implementation of {@link UserInteractionLogService} for user interaction log operations. */
@Service
public class UserInteractionLogServiceImpl implements UserInteractionLogService {

  /** Repository for user interaction logs. */
  @Autowired private UserInteractionLogRepository userInteractionLogRepository;

  /** {@inheritDoc} */
  @Override
  public Page<GetUserInteractionLogDto> findAll(Pageable pageable) {
    return userInteractionLogRepository.findAll(pageable).map(UserInteractionLogMapper::toDto);
  }

  /** {@inheritDoc} */
  @Override
  public Page<GetUserInteractionLogDto> findByUsername(Pageable pageable, String username) {
    return userInteractionLogRepository
        .findByUsername(pageable, username)
        .map(UserInteractionLogMapper::toDto);
  }
}
