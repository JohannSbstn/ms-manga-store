package com.spring.boot.project.ms.manga.store.application.exception;

public class PasswordNotMatchException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Both password and confirm password should be the same";

    public PasswordNotMatchException() {
        super(MESSAGE_TEMPLATE);
    }
}
