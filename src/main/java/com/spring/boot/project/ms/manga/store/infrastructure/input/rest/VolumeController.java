package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.request.VolumeRequestDto;
import com.spring.boot.project.ms.manga.store.application.service.VolumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/volume")
public class VolumeController {

    private final VolumeService volumeService;

    @PostMapping
    public ResponseEntity<HttpStatus> createVolume(@RequestBody @Valid VolumeRequestDto volumeRequestDto) {
        volumeService.create(volumeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<HttpStatus> switchVolumeStatus(@PathVariable String isbn) {
        volumeService.switchVolumeStatus(isbn);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}