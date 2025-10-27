package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.MangaRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mangas")
public class MangaController {
    @PostMapping
    public ResponseEntity<HttpStatus> createManga(@RequestBody MangaRequestDto mangaRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
