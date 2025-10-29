package com.spring.boot.project.ms.manga.store.application.service;

import com.spring.boot.project.ms.manga.store.application.dto.request.UserRequestDto;

public interface UserService {
    void createUser(UserRequestDto userRequestDto);

    void createAdmin(UserRequestDto userRequestDto);
}
