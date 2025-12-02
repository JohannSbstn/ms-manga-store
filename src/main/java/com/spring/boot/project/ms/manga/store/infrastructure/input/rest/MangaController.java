package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;
import com.spring.boot.project.ms.manga.store.application.service.MangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manga")
public class MangaController {

    private final MangaService mangaService;

    @GetMapping("/{id}")
    public ResponseEntity<MangaResponseDto> getMangaById(@PathVariable Long id) {
        MangaResponseDto mangaResponseDto = mangaService.get(id);
        return ResponseEntity.ok(mangaResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<MangaResponseDto>> getAll(Pageable pageable) {
        Page<MangaResponseDto> mangaResponseDto = mangaService.getAll(pageable);
        return ResponseEntity.ok(mangaResponseDto);

    }
}
