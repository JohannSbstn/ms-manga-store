package com.spring.boot.project.ms.manga.store.infrastructure.advice;

import com.spring.boot.project.ms.manga.store.application.dto.response.ErrorResponseDto;
import com.spring.boot.project.ms.manga.store.application.exception.PasswordNotMatchException;
import com.spring.boot.project.ms.manga.store.domain.exception.UserEmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        String details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::formatFieldError)
                .collect(Collectors.joining("; "));

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                Integer.toString(HttpStatus.BAD_REQUEST.value()),
                LocalDateTime.now(),
                "Validation Error",
                details
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> userEmailAlreadyExists(UserEmailAlreadyExistsException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                Integer.toString(HttpStatus.CONFLICT.value()),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDto);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ErrorResponseDto> passwordNotMatch(PasswordNotMatchException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                Integer.toString(HttpStatus.BAD_REQUEST.value()),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    private String formatFieldError(FieldError fe) {
        String field = fe.getField();
        String msg = fe.getDefaultMessage();
        Object rejected = fe.getRejectedValue();
        return rejected == null ? (field + ": " + msg) : (field + " (" + rejected + "): " + msg);
    }
}
