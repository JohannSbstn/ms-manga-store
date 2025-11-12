package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.exception.UserEmailAlreadyExistsException;
import com.spring.boot.project.ms.manga.store.domain.exception.UserIdentityDocumentAlreadyExistsException;
import com.spring.boot.project.ms.manga.store.domain.model.User;
import com.spring.boot.project.ms.manga.store.domain.output.UserPortOut;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.UserEntity;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserJpaAdapter implements UserPortOut {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserEmailAlreadyExistsException(user.getEmail());
        }

        if (userRepository.existsByIdentityDocument(user.getIdentityDocument())) {
            throw new UserIdentityDocumentAlreadyExistsException(user.getIdentityDocument());
        }

        UserEntity userEntity = userEntityMapper.toEntity(user);
        userEntity.setCreatedAt(LocalDateTime.now());
        userRepository.save(userEntity);
    }
}
