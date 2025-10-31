package com.spring.boot.project.ms.manga.store.domain.exception;

public class MangaNotExistException extends RuntimeException {
    private final static String MESSAGE_TEMPLATE = "the manga with id: %s is not registered";
    public MangaNotExistException(Long id) {
        super(String.format(MESSAGE_TEMPLATE, id));
    }
}
