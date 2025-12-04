package com.spring.boot.project.ms.manga.store.infrastructure.advice;

import com.spring.boot.project.ms.manga.store.application.exception.PasswordNotMatchException;
import com.spring.boot.project.ms.manga.store.domain.exception.MangaNotExistException;
import com.spring.boot.project.ms.manga.store.domain.exception.UserEmailAlreadyExistsException;
import com.spring.boot.project.ms.manga.store.domain.exception.UserIdentityDocumentAlreadyExistsException;
import com.spring.boot.project.ms.manga.store.domain.exception.VolumeAlreadyRegisteredException;
import com.spring.boot.project.ms.manga.store.domain.exception.VolumeNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        String details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
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

    @ExceptionHandler(UserIdentityDocumentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> userIdentityDocumentAlreadyExists(
            UserIdentityDocumentAlreadyExistsException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                Integer.toString(HttpStatus.CONFLICT.value()),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> genericException(Exception ex) {
        log.error(ex.getMessage());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                Integer.toString(httpStatus.value()),
                LocalDateTime.now(),
                httpStatus.getReasonPhrase(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(httpStatus.value()).body(errorResponseDto);
    }

    public record ErrorResponseDto(String code, LocalDateTime timestamp, String description, String exception) {
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

    @ExceptionHandler(VolumeNotExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleVolumeNotExitsException(
            VolumeNotExistsException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                Integer.toString(HttpStatus.NOT_FOUND.value()),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }
}
