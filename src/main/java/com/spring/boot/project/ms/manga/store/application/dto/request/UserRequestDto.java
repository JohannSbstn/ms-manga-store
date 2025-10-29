package com.spring.boot.project.ms.manga.store.application.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {

    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private String lastname;

}
