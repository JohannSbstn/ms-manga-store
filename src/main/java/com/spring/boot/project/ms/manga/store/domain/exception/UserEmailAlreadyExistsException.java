package com.spring.boot.project.ms.manga.store.domain.exception;

public class UserEmailAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "The User with email: %s is already registered.";

    public UserEmailAlreadyExistsException(String email) {
        super(String.format(MESSAGE_TEMPLATE, email));
    }
}
