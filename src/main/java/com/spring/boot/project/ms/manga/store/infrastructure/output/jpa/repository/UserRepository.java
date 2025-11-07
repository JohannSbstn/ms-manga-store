package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository;

import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByDni(String dni);
}
