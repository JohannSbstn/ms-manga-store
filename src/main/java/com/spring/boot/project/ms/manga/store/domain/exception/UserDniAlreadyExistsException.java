package com.spring.boot.project.ms.manga.store.domain.exception;

public class UserDniAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "DNI: %s is duplicated";

    public UserDniAlreadyExistsException(String dni) {
        super(String.format(MESSAGE_TEMPLATE, dni));
    }
}
