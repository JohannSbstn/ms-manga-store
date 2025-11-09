package com.spring.boot.project.ms.manga.store.infrastructure.advice;

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

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Error");
        response.put("messages", errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(VolumeAlreadyRegisteredException.class)
    public ResponseEntity<Map<String, Object>> handleVolumeAlreadyRegisteredException(VolumeAlreadyRegisteredException ex) {

        return buildErrorResponse(ex, HttpStatus.CONFLICT, "Volume already registered");
    }

    @ExceptionHandler(MangaNotExistException.class)
    public ResponseEntity<Map<String, Object>> handleMangaNotExistException(MangaNotExistException ex) {

        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, "Manga not found");
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(Exception ex, HttpStatus status, String error) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", error);
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, status);
    }
}
