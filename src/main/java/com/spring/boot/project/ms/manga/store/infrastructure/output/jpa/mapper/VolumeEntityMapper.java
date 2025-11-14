package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper;

import com.spring.boot.project.ms.manga.store.domain.model.Volume;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.VolumeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface VolumeEntityMapper {
    Volume toModel(VolumeEntity volumeEntity);

    VolumeEntity toEntity(Volume volume);
}
