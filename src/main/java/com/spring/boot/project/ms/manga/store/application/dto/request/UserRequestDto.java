package com.spring.boot.project.ms.manga.store.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "DNI is required")
    private String dni;

    @Email(message = "Email format is incorrect")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must have between 8 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have at least one uppercase, one lowercase, one number and one special character"
    )
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[\\p{L} ]+$",
            message = "Name cannot contain numbers or special characters")
    private String name;

    @NotBlank(message = "Lastname is required")
    @Pattern(regexp = "^[\\p{L} ]+$",
            message = "Lastname cannot contain numbers or special characters")
    private String lastname;

}
