package com.spring.boot.project.ms.manga.store.application.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserRequestDto {

    private String dni;
    @Email
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private String lastname;

}
