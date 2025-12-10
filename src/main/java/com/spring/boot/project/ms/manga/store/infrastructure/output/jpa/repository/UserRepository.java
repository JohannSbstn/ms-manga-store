package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository;

import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles WHERE u.email = :email")
    Optional<UserEntity> findByEmailWithRoles(@Param("email") String email);

    boolean existsByEmail(String email);

    boolean existsByIdentityDocument(String identityDocument);

    Optional<UserEntity> findByEmail(String email);
}
