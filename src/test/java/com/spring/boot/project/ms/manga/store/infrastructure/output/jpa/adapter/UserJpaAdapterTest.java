package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.exception.UserEmailAlreadyExistsException;
import com.spring.boot.project.ms.manga.store.domain.model.User;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity.UserEntity;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @Test
    void create_WhenEmailDoesNotExist_ShouldSaveUser() {
        // Arrange
        User user = User.builder()
                .email("test@example.com")
                .name("John")
                .lastname("Doe")
                .build();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");

        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        // Act
        userJpaAdapter.create(user);

        // Assert
        verify(userRepository, times(1)).existsByEmail("test@example.com");
        verify(userEntityMapper, times(1)).toEntity(user);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void create_WhenEmailAlreadyExists_ShouldThrowUserEmailAlreadyExistsException() {
        // Arrange
        User user = User.builder()
                .email("existing@example.com")
                .build();

        String expectedMessage = "The User with email: existing@example.com is already registered.";

        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        // Act & Assert
        UserEmailAlreadyExistsException exception = assertThrows(
                UserEmailAlreadyExistsException.class,
                () -> userJpaAdapter.create(user)
        );

        assertEquals(expectedMessage, exception.getMessage());
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}