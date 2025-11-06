package com.spring.boot.project.ms.manga.store.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordNotMatchExceptionTest {

    @Test
    void createException_ShouldHaveDefaultMessage() {
        // Arrange
        String expectedMessage = "Both password and confirm password should be the same";

        // Act
        PasswordNotMatchException exception = new PasswordNotMatchException();

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void createMultipleExceptions_ShouldAllHaveSameMessage() {
        // Act
        PasswordNotMatchException exception1 = new PasswordNotMatchException();
        PasswordNotMatchException exception2 = new PasswordNotMatchException();

        // Assert
        assertEquals(exception1.getMessage(), exception2.getMessage());
        assertEquals("Both password and confirm password should be the same", exception1.getMessage());
    }
}