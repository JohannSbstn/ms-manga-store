package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.model.User;
import com.spring.boot.project.ms.manga.store.domain.output.UserPortOut;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private UserPortOut userPortOut;

    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    void create_ShouldCallPortOut() {
        // Arrange
        User user = User.builder()
                .identityDocument("12345678")
                .email("test@example.com")
                .password("password")
                .name("John")
                .lastname("Doe")
                .active(true)
                .build();

        // Act
        userUseCase.create(user);

        // Assert
        verify(userPortOut, times(1)).create(user);
    }
}