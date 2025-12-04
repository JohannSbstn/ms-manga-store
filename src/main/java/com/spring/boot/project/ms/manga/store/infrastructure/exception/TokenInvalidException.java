package com.spring.boot.project.ms.manga.store.infrastructure.exception;

public class TokenInvalidException extends RuntimeException {

    private static final String TEMPLATE_MESSAGE = "Token is invalid";

    public TokenInvalidException() {
        super(TEMPLATE_MESSAGE);
    }
}
