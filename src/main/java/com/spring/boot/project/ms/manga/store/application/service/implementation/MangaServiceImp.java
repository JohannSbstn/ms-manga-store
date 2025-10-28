package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.request.MangaRequestDto;
import com.spring.boot.project.ms.manga.store.application.service.MangaService;
import com.spring.boot.project.ms.manga.store.domain.input.MangaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MangaServiceImp implements MangaService {

    private final MangaPortIn mangaPortIn;

    @Override
    public void create(MangaRequestDto mangaRequestDto) {
        System.out.println(mangaRequestDto);
    }
}
