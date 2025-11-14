package com.spring.boot.project.ms.manga.store.domain.exception;

public class VolumeAlreadyRegisteredException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "the manga with isbn: %s is already registered";

    public VolumeAlreadyRegisteredException(String isbn) {
        super(String.format(MESSAGE_TEMPLATE, isbn));
    }
}
