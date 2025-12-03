package com.spring.boot.project.ms.manga.store.domain.exception;

public class VolumeNotExistsException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "the volume with isbn: %s is already registered";

    public VolumeNotExistsException(String isbn) {
        super(String.format(MESSAGE_TEMPLATE, isbn));
    }
}
