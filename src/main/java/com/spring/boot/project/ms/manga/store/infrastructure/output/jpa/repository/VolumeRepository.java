package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository;

import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.VolumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolumeRepository extends JpaRepository<VolumeEntity, Long> {
    boolean existsByIsbn(String isbn);
}
