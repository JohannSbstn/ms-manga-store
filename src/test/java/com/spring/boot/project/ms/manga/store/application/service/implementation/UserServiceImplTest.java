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
class UserServiceImplTest {

    @Mock
    private UserPortIn userPortIn;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void createUser_WhenPasswordsMatch_ShouldCreateUser() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto(
                "12345678",                    // identityDocument
                "test@example.com",            // email
                "Password123!",                // password
                "Password123!",                // confirmPassword
                "John",                        // name
                "Doe"                          // lastname
        );

        when(passwordEncoder.encode("Password123!")).thenReturn("encodedPassword");

        // Act
        userServiceImpl.createUser(userRequestDto);

        // Assert
        verify(userPortIn).create(any(User.class));
        verify(passwordEncoder).encode("Password123!");
    }

    @Test
    void createUser_WhenPasswordsDoNotMatch_ShouldThrowPasswordNotMatchException() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto(
                "12345678",                    // identityDocument
                "test@example.com",            // email
                "Password123!",                // password
                "DifferentPassword123!",       // confirmPassword (diferente)
                "John",                        // name
                "Doe"                          // lastname
        );

        String expectedMessage = "Both password and confirm password should be the same";

        // Act & Assert
        PasswordNotMatchException exception = assertThrows(
                PasswordNotMatchException.class,
                () -> userServiceImpl.createUser(userRequestDto)
        );

        assertEquals(expectedMessage, exception.getMessage());
        verify(userPortIn, never()).create(any(User.class));
        verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    void createAdmin_WhenPasswordsDoNotMatch_ShouldThrowPasswordNotMatchException() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto(
                "12345678",                    // identityDocument
                "admin@example.com",           // email
                "Admin123!",                   // password
                "DifferentAdmin123!",          // confirmPassword (diferente)
                "Admin",                       // name
                "User"                         // lastname
        );

        String expectedMessage = "Both password and confirm password should be the same";

        // Act & Assert
        PasswordNotMatchException exception = assertThrows(
                PasswordNotMatchException.class,
                () -> userServiceImpl.createAdmin(userRequestDto)
        );

        assertEquals(expectedMessage, exception.getMessage());
        verify(userPortIn, never()).create(any(User.class));
        verify(passwordEncoder, never()).encode(anyString());
    }
}