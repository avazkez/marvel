package com.avazquez.api.marvel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.avazquez.api.marvel.criteria.CharacterSearchCriteria;
import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.avazquez.api.marvel.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
    value = CharacterController.class,
    excludeFilters = {
      @ComponentScan.Filter(
          type = FilterType.ASSIGNABLE_TYPE,
          classes = {
            com.avazquez.api.marvel.web.interceptor.UserInteractionInterceptor.class,
            com.avazquez.api.marvel.web.interceptor.InterceptorsConfig.class
          })
    })
class CharacterControllerIT {

  @Autowired private MockMvc mockMvc;

  @MockBean private CharacterService characterService;

  @MockBean private com.avazquez.api.marvel.service.JwtService jwtService;

  @MockBean
  private com.avazquez.api.marvel.persistence.repository.UserInteractionLogRepository
      userInteractionLogRepository;

  @MockBean private com.avazquez.api.marvel.service.AuthenticationService authenticationService;

  @WithMockUser(authorities = {"character:read-all"})
  @Test
  void findAll_ReturnsCharacters() throws Exception {
    CharacterDto dto = new CharacterDto(1L, "Spider-Man", "Hero", "2025-10-26", "uri/1");
    when(characterService.findAll(any(MyPageable.class), any(CharacterSearchCriteria.class)))
        .thenReturn(java.util.List.of(dto));

    mockMvc
        .perform(
            get("/api/characters")
                .param("name", "Spider-Man")
                .param("offset", "0")
                .param("limit", "10")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name").value("Spider-Man"));
  }

  @WithMockUser(authorities = {"character:read-detail"})
  @Test
  void findCharacterById_ReturnsCharacterInfo() throws Exception {
    CharacterDto.CharacterInfoDto infoDto =
        new CharacterDto.CharacterInfoDto("image/path.jpg", "Hero details");
    when(characterService.findInfoById(1L)).thenReturn(infoDto);

    mockMvc
        .perform(get("/api/characters/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.imagePath").value("image/path.jpg"));
  }

  @WithMockUser(authorities = {"character:read-detail"})
  @Test
  void findCharacterById_Returns404_WhenNotFound() throws Exception {
    when(characterService.findInfoById(999L))
        .thenThrow(new jakarta.persistence.EntityNotFoundException("Not found"));

    mockMvc
        .perform(get("/api/characters/999").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
