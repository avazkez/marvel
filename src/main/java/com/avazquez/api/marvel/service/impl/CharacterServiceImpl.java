package com.avazquez.api.marvel.service.impl;

import com.avazquez.api.marvel.criteria.CharacterSearchCriteria;
import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.avazquez.api.marvel.persistence.integration.marvel.repository.CharacterRepository;
import com.avazquez.api.marvel.service.CharacterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of CharacterService for Marvel character business operations.
 *
 * <p>This service implementation provides the business logic for character operations, acting as an
 * intermediary between the controller layer and data access layer. It handles data transformation,
 * validation, and coordination with external APIs.
 *
 * <p>Receives a {@link CharacterSearchCriteria} object for filtering and searching characters.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Service
public class CharacterServiceImpl implements CharacterService {

  @Autowired private CharacterRepository characterRepository;

  /** {@inheritDoc} */
  @Override
  public List<CharacterDto> findAll(MyPageable pageable, CharacterSearchCriteria criteria) {
    return characterRepository.findAll(pageable, criteria);
  }

  /** {@inheritDoc} */
  @Override
  public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
    return characterRepository.findInfoById(characterId);
  }
}
