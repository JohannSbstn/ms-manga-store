package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository;

import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.MangaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaRepository extends JpaRepository<MangaEntity, Long> {
}
