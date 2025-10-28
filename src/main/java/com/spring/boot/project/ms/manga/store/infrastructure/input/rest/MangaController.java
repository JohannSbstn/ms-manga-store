package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.request.MangaRequestDto;
import com.spring.boot.project.ms.manga.store.application.service.MangaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mangas")
public class MangaController {

    private final MangaService mangaService;

    @PostMapping
    public ResponseEntity<HttpStatus> createManga(@RequestBody @Valid MangaRequestDto mangaRequestDto){
        mangaService.create(mangaRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
