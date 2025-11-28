package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.exception.UserEmailAlreadyExistsException;
import com.spring.boot.project.ms.manga.store.domain.exception.UserIdentityDocumentAlreadyExistsException;
import com.spring.boot.project.ms.manga.store.domain.model.Role;
import com.spring.boot.project.ms.manga.store.domain.model.User;
import com.spring.boot.project.ms.manga.store.domain.output.UserPortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.RoleEntity;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.UserEntity;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.RoleRepository;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class UserJpaAdapter implements UserPortOut {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void create(User user) {
        if (userRepository.existsByEmail(user.email())) {
            throw new UserEmailAlreadyExistsException(user.email());
        }

        if (userRepository.existsByIdentityDocument(user.identityDocument())) {
            throw new UserIdentityDocumentAlreadyExistsException(user.identityDocument());
        }

        UserEntity userEntity = userEntityMapper.toEntity(user);

        Set<RoleEntity> roleEntities = new HashSet<>();
        for (Role role : user.roles()) {
            RoleEntity roleEntity = roleRepository.findById(getRoleId(role))
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            roleEntities.add(roleEntity);
        }

        userEntity.setRoles(roleEntities);
        userRepository.save(userEntity);
    }

    private Integer getRoleId(Role role) {
        return switch (role) {
            case ADMIN -> 1;
            case USER -> 2;
        };
    }
}
