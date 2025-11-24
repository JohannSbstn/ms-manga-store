package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper;

import com.spring.boot.project.ms.manga.store.domain.model.Manga;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.MangaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MangaEntityMapper {
    Manga toModel(MangaEntity mangaEntity);
}
