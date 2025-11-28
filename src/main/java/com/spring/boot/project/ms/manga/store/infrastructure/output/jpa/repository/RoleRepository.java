package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository;

import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}
