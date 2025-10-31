package com.spring.boot.project.ms.manga.store.application.service;

import com.spring.boot.project.ms.manga.store.application.dto.request.VolumeRequestDto;

public interface VolumeService {
    void create(VolumeRequestDto volumeRequestDto);
}
