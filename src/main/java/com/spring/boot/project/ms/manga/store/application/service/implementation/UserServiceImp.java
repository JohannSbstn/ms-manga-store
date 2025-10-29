package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.request.UserRequestDto;
import com.spring.boot.project.ms.manga.store.application.exception.PasswordNotMatchException;
import com.spring.boot.project.ms.manga.store.application.service.UserService;
import com.spring.boot.project.ms.manga.store.domain.input.UserPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Role;
import com.spring.boot.project.ms.manga.store.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserPortIn userPortIn;

    @Override
    public void createUser(UserRequestDto userRequestDto) {
        if (!userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())) {
            throw new PasswordNotMatchException();
        }
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .name(userRequestDto.getName())
                .lastname(userRequestDto.getLastname())
                .active(true)
                .roles(EnumSet.of(Role.USER))
                .build();
        userPortIn.create(user);
    }

    @Override
    public void createAdmin(UserRequestDto userRequestDto) {
        if (!userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())) {
            throw new PasswordNotMatchException();
        }
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .name(userRequestDto.getName())
                .lastname(userRequestDto.getLastname())
                .active(true)
                .roles(EnumSet.of(Role.ADMIN))
                .build();
        userPortIn.create(user);
    }
}
