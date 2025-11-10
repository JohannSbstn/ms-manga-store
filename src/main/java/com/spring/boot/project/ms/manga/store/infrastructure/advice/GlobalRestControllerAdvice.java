package com.spring.boot.project.ms.manga.store.infrastructure.advice;

import com.spring.boot.project.ms.manga.store.application.dto.response.ErrorResponseDto;
import com.spring.boot.project.ms.manga.store.domain.exception.MangaNotExistException;
import com.spring.boot.project.ms.manga.store.domain.exception.VolumeAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    // 🧩 Manejo de errores de validación (DTOs con @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Error");
        response.put("messages", errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(VolumeAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponseDto> handleVolumeAlreadyRegisteredException(
            VolumeAlreadyRegisteredException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                Integer.toString(HttpStatus.CONFLICT.value()),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDto);
    }

    @ExceptionHandler(MangaNotExistException.class)
    public ResponseEntity<ErrorResponseDto> handleMangaNotExistException(
            MangaNotExistException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                Integer.toString(HttpStatus.NOT_FOUND.value()),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }
}
