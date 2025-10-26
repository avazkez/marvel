package com.avazquez.api.marvel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.avazquez.api.marvel.criteria.CharacterSearchCriteria;
import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.avazquez.api.marvel.persistence.integration.marvel.repository.CharacterRepository;
import com.avazquez.api.marvel.service.impl.CharacterServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CharacterServiceImplTest {
  @Mock private CharacterRepository characterRepository;

  @InjectMocks private CharacterServiceImpl characterService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindAll_ReturnsCharacterList() {
    MyPageable pageable = mock(MyPageable.class);
    CharacterSearchCriteria criteria = mock(CharacterSearchCriteria.class);
    CharacterDto char1 = new CharacterDto(1L, "Spider-Man", "Hero", "2024-01-01", "uri1");
    CharacterDto char2 = new CharacterDto(2L, "Iron Man", "Hero", "2024-01-02", "uri2");
    List<CharacterDto> expected = Arrays.asList(char1, char2);
    when(characterRepository.findAll(pageable, criteria)).thenReturn(expected);

    List<CharacterDto> result = characterService.findAll(pageable, criteria);
    assertEquals(expected, result);
    verify(characterRepository).findAll(pageable, criteria);
  }

  @Test
  void testFindInfoById_ReturnsCharacterInfo() {
    CharacterDto.CharacterInfoDto info =
        new CharacterDto.CharacterInfoDto("img/path", "Hero details");
    when(characterRepository.findInfoById(1L)).thenReturn(info);

    CharacterDto.CharacterInfoDto result = characterService.findInfoById(1L);
    assertEquals(info, result);
    verify(characterRepository).findInfoById(1L);
  }
}
