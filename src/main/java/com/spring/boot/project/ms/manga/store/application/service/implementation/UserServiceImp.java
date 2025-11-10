package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.request.UserRequestDto;
import com.spring.boot.project.ms.manga.store.application.exception.PasswordNotMatchException;
import com.spring.boot.project.ms.manga.store.application.service.UserService;
import com.spring.boot.project.ms.manga.store.domain.input.UserPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.Role;
import com.spring.boot.project.ms.manga.store.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserPortIn userPortIn;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserRequestDto userRequestDto) {
        createUserOrAdmin(EnumSet.of(Role.USER), userRequestDto);
    }

    @Override
    public void createAdmin(UserRequestDto userRequestDto) {
        createUserOrAdmin(EnumSet.of(Role.ADMIN), userRequestDto);
    }

    private void createUserOrAdmin(EnumSet<Role> roles, UserRequestDto userRequestDto) {
        if (!userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())) {
            throw new PasswordNotMatchException();
        }
        User user = User.builder()
                .identityDocument(userRequestDto.getIdentityDocument())
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .name(userRequestDto.getName())
                .lastname(userRequestDto.getLastname())
                .active(true)
                .roles(roles)
                .build();
        userPortIn.create(user);
    }
}
