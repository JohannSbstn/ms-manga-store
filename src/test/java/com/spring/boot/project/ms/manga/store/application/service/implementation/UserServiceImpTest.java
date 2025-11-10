package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.request.UserRequestDto;
import com.spring.boot.project.ms.manga.store.application.exception.PasswordNotMatchException;
import com.spring.boot.project.ms.manga.store.domain.input.UserPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    private UserPortIn userPortIn;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImp userServiceImp;

    @Test
    void createUser_WhenPasswordsMatch_ShouldCreateUser() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setIdentityDocument("12345678");
        userRequestDto.setEmail("test@example.com");
        userRequestDto.setPassword("password123");
        userRequestDto.setConfirmPassword("password123");
        userRequestDto.setName("John");
        userRequestDto.setLastname("Doe");

        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        // Act
        userServiceImp.createUser(userRequestDto);

        // Assert
        verify(userPortIn).create(any(User.class));
        verify(passwordEncoder).encode("password123");
    }

    @Test
    void createUser_WhenPasswordsDoNotMatch_ShouldThrowPasswordNotMatchException() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setPassword("password123");
        userRequestDto.setConfirmPassword("differentPassword");

        String expectedMessage = "Both password and confirm password should be the same";

        // Act & Assert
        PasswordNotMatchException exception = assertThrows(
                PasswordNotMatchException.class,
                () -> userServiceImp.createUser(userRequestDto)
        );

        assertEquals(expectedMessage, exception.getMessage());
        verify(userPortIn, never()).create(any(User.class));
        verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    void createAdmin_WhenPasswordsDoNotMatch_ShouldThrowPasswordNotMatchException() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setPassword("admin123");
        userRequestDto.setConfirmPassword("differentAdminPassword");

        String expectedMessage = "Both password and confirm password should be the same";

        // Act & Assert
        PasswordNotMatchException exception = assertThrows(
                PasswordNotMatchException.class,
                () -> userServiceImp.createAdmin(userRequestDto)
        );

        assertEquals(expectedMessage, exception.getMessage());
        verify(userPortIn, never()).create(any(User.class));
        verify(passwordEncoder, never()).encode(anyString());
    }
}