package com.spring.boot.project.ms.manga.store.domain.exception;

public class MangaAlreadyRegisteredException extends RuntimeException {
    private final static String MESSAGE_TEMPLATE = "the manga with isbn: %s is already registered";
    public MangaAlreadyRegisteredException(String isbn) {
        super(String.format(MESSAGE_TEMPLATE, isbn));
    }
}
