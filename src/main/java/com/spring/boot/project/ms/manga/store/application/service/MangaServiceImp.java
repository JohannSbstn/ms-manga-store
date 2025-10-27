package com.spring.boot.project.ms.manga.store.application.service;

import com.spring.boot.project.ms.manga.store.application.dto.MangaRequestDto;
import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MangaServiceImp implements MangaService{

    private final MangaPortIn mangaPortIn;

    @Override
    public void create(MangaRequestDto mangaRequestDto) {

    }
}
