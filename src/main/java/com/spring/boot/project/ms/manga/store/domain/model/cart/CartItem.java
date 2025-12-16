package com.spring.boot.project.ms.manga.store.domain.model.cart;

public record CartItem(
        String isbn,
        int quantity,
        double price) {
}