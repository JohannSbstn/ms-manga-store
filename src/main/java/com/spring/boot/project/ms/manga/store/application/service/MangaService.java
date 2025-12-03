package com.spring.boot.project.ms.manga.store.application.service;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;

public interface MangaService {

    MangaResponseDto getById(Long mangaId);
}
