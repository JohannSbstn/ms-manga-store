package com.spring.boot.project.ms.manga.store.application.service;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MangaService {

    MangaResponseDto get(Long mangaId);

    Page<MangaResponseDto> getAll(Pageable pageable);
}
