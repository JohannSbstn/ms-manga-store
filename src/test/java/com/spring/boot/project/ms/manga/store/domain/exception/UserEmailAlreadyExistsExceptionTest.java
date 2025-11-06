package com.spring.boot.project.ms.manga.store.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEmailAlreadyExistsExceptionTest {

    @Test
    void createException_WithEmail_ShouldSetFormattedMessage() {
        // Arrange
        String email = "test@example.com";
        String expectedMessage = "The User with email: test@example.com is already registered.";

        // Act
        UserEmailAlreadyExistsException exception = new UserEmailAlreadyExistsException(email);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void createException_WithDifferentEmail_ShouldSetCorrectMessage() {
        // Arrange
        String email = "user@domain.com";
        String expectedMessage = "The User with email: user@domain.com is already registered.";

        // Act
        UserEmailAlreadyExistsException exception = new UserEmailAlreadyExistsException(email);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void createException_WithNullEmail_ShouldHandleNull() {
        // Arrange
        String email = null;
        String expectedMessage = "The User with email: null is already registered.";

        // Act
        UserEmailAlreadyExistsException exception = new UserEmailAlreadyExistsException(email);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}