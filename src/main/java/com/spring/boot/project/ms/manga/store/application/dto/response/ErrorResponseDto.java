package com.spring.boot.project.ms.manga.store.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String code;
    private LocalDateTime timestamp;
    private String description;
    private String exception;
}
