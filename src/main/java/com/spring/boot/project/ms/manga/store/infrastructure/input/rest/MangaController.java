package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.response.MangaResponseDto;
import com.spring.boot.project.ms.manga.store.application.service.MangaService;
import com.spring.boot.project.ms.manga.store.domain.common.Page;
import com.spring.boot.project.ms.manga.store.domain.common.RequestPage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ResponseEntity<Page<MangaResponseDto>> getAll(@ModelAttribute RequestPage pageRequest) {
        return ResponseEntity.ok(mangaService.getAll(pageRequest));

    }
}
