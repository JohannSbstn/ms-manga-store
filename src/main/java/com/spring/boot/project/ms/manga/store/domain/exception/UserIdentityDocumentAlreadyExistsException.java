package com.spring.boot.project.ms.manga.store.domain.exception;

public class UserIdentityDocumentAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Identity document: %s is duplicated";

    public UserIdentityDocumentAlreadyExistsException(String identityDocument) {
        super(String.format(MESSAGE_TEMPLATE, identityDocument));
    }
}
