package com.spring.boot.project.ms.manga.store.infrastructure.exception;

public class TokenExpiredException extends RuntimeException {

    private static final String TEMPLATE_MESSAGE = "Token is expired";

    public TokenExpiredException() {
        super(TEMPLATE_MESSAGE);
    }
}
