package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.request.UserRequestDto;
import com.spring.boot.project.ms.manga.store.application.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserRequestDto buildUserRequest() {
        return new UserRequestDto(
                "12345678",
                "test@example.com",
                "password123",
                "password123",
                "John",
                "Doe"
        );
    }

    @Test
    void createUser_ShouldReturnCreatedStatusAndInvokeService() {
        UserRequestDto userRequestDto = buildUserRequest();
        doNothing().when(userService).createUser(userRequestDto);

        ResponseEntity<HttpStatus> response = userController.createUser(userRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService, times(1)).createUser(userRequestDto);
    }

    @Test
    void createAdmin_ShouldReturnCreatedStatusAndInvokeService() {
        UserRequestDto userRequestDto = buildUserRequest();
        doNothing().when(userService).createAdmin(userRequestDto);

        ResponseEntity<HttpStatus> response = userController.createAdmin(userRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService, times(1)).createAdmin(userRequestDto);
    }
}
