package com.spring.boot.project.ms.manga.store.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "UserRequest",
        description = "Payload to register a new user with USER role"
)
public record UserRequestDto(

        @Schema(
                description = "User identity document number",
                example = "1234567890"
        )
        @NotBlank(message = "Identity document is required")
        String identityDocument,

        @Schema(
                description = "User email address",
                example = "user@example.com"
        )
        @Email(message = "Email format is incorrect")
        @NotBlank(message = "Email is required")
        String email,

        @Schema(
                description = "User password. Must contain 8-20 chars, at least one uppercase, one lowercase, "
                        + "one digit and one special character",
                example = "Str0ngP@ss!"
        )
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 20, message = "Password must have between 8 and 20 characters")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must have at least one uppercase, one lowercase, one number and one special "
                        + "character"
        )
        String password,

        @Schema(
                description = "Password confirmation. Must match the password field",
                example = "Str0ngP@ss!"
        )
        @NotBlank(message = "Confirm password is required")
        String confirmPassword,

        @Schema(
                description = "User first name",
                example = "John"
        )
        @NotBlank(message = "Name is required")
        String name,

        @Schema(
                description = "User last name",
                example = "Doe"
        )
        @NotBlank(message = "Lastname is required")
        String lastname
) {}
