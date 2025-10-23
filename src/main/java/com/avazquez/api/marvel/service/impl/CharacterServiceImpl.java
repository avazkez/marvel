package com.avazquez.api.marvel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.avazquez.api.marvel.persistence.integration.marvel.repository.CharacterRepository;
import com.avazquez.api.marvel.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series){
        return characterRepository.findAll(pageable, name, comics, series);
    }

    @Override
    public CharacterDto.CharacterInfoDto findInfoById(Long characterId){
        return characterRepository.findInfoById(characterId);
    }
}
