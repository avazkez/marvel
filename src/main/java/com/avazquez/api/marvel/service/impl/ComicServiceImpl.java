package com.avazquez.api.marvel.service.impl;

import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.ComicDto;
import com.avazquez.api.marvel.persistence.integration.marvel.repository.ComicRepository;
import com.avazquez.api.marvel.service.ComicService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of ComicService for Marvel comic business operations.
 *
 * <p>This service implementation provides the business logic for comic operations, acting as an
 * intermediary between the controller layer and data access layer. It handles data transformation,
 * validation, and coordination with external APIs.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Service
public class ComicServiceImpl implements ComicService {

  @Autowired private ComicRepository comicRepository;

  @Override
  public List<ComicDto> findAll(MyPageable pageable, Long characterId) {
    return comicRepository.findAll(pageable, characterId);
  }

  @Override
  public ComicDto findById(Long comicId) {
    return comicRepository.findById(comicId);
  }
}
