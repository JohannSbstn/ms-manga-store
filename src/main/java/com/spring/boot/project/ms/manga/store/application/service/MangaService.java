package com.spring.boot.project.ms.manga.store.application.service;

import com.spring.boot.project.ms.manga.store.application.dto.request.MangaRequestDto;

public interface MangaService {
    void create(MangaRequestDto mangaRequestDto);
}
