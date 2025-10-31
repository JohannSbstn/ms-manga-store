package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.exception.VolumeAlreadyRegisteredException;
import com.spring.boot.project.ms.manga.store.domain.model.Volume;
import com.spring.boot.project.ms.manga.store.domain.output.VolumePortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.VolumeEntity;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.VolumeEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.VolumeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VolumeJpaAdapter implements VolumePortOut {
    private final VolumeRepository volumeRepository;
    private final VolumeEntityMapper volumeEntityMapper;

    @Override
    public void create(Volume volume) {
        if (volumeRepository.existsByIsbn(volume.getIsbn())){
            throw new VolumeAlreadyRegisteredException(volume.getIsbn());
        }
        VolumeEntity volumeEntity = volumeEntityMapper.toEntity(volume);
        volumeEntity.setMangaId(volume.getManga().getId());
        volumeRepository.save(volumeEntity);
    }
}
