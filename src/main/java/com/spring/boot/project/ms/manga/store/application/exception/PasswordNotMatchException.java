package com.spring.boot.project.ms.manga.store.application.exception;

public class PasswordNotMatchException extends RuntimeException {
    private final static String MESSAGE_TEMPLATE = "The password does not match.";
    public PasswordNotMatchException() {
        super(MESSAGE_TEMPLATE);
    }
}
