package com.spring.boot.project.ms.manga.store.application.service;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;
import com.spring.boot.project.ms.manga.store.domain.common.Page;
import com.spring.boot.project.ms.manga.store.domain.common.RequestPage;

public interface MangaService {

    Page<MangaResponseDto> getAll(RequestPage pageRequest);

    MangaResponseDto getById(Long mangaId);
}
