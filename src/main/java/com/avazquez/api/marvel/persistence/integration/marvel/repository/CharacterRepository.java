package com.avazquez.api.marvel.persistence.integration.marvel.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;

@Repository
public class CharacterRepository {

    public List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
        // Implementation goes here
        return null;
    }

    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        // Implementation goes here
        return null;
    }
}
