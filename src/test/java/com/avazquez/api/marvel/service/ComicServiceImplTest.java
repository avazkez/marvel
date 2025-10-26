package com.avazquez.api.marvel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.avazquez.api.marvel.criteria.ComicSearchCriteria;
import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.ComicDto;
import com.avazquez.api.marvel.persistence.integration.marvel.repository.ComicRepository;
import com.avazquez.api.marvel.service.impl.ComicServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ComicServiceImplTest {
  @Mock private ComicRepository comicRepository;

  @InjectMocks private ComicServiceImpl comicService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindAll_ReturnsComicList() {
    MyPageable pageable = mock(MyPageable.class);
    ComicSearchCriteria criteria = mock(ComicSearchCriteria.class);
    ComicDto comic1 = new ComicDto(1L, "Title1", "Desc1", "2024-01-01", "uri1", null);
    ComicDto comic2 = new ComicDto(2L, "Title2", "Desc2", "2024-01-02", "uri2", null);
    List<ComicDto> expected = Arrays.asList(comic1, comic2);
    when(comicRepository.findAll(pageable, criteria)).thenReturn(expected);

    List<ComicDto> result = comicService.findAll(pageable, criteria);
    assertEquals(expected, result);
    verify(comicRepository).findAll(pageable, criteria);
  }

  @Test
  void testFindById_ReturnsComic() {
    ComicDto comic = new ComicDto(1L, "Title1", "Desc1", "2024-01-01", "uri1", null);
    when(comicRepository.findById(1L)).thenReturn(comic);

    ComicDto result = comicService.findById(1L);
    assertEquals(comic, result);
    verify(comicRepository).findById(1L);
  }
}
