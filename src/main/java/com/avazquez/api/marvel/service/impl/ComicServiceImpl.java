package com.avazquez.api.marvel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avazquez.api.marvel.dto.MyPageable;
import com.avazquez.api.marvel.persistence.integration.marvel.dto.ComicDto;
import com.avazquez.api.marvel.persistence.integration.marvel.repository.ComicRepository;
import com.avazquez.api.marvel.service.ComicService;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicRepository comicRepository;

    @Override
    public List<ComicDto> findAll(MyPageable pageable, Long characterId){
        return comicRepository.findAll(pageable, characterId);
    }

    @Override
    public ComicDto findById(Long comicId){
        return comicRepository.findById(comicId);
    }

}
