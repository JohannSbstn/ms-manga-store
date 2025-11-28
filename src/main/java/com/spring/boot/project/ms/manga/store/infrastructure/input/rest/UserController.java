package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.request.UserRequestDto;
import com.spring.boot.project.ms.manga.store.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-up/admin")
    public ResponseEntity<HttpStatus> createAdmin(@RequestBody @Valid UserRequestDto userRequestDto) {
        userService.createAdmin(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
