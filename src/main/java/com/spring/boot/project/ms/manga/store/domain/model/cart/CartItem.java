package com.spring.boot.project.ms.manga.store.domain.model.cart;

import java.math.BigDecimal;

public record CartItem(
        String isbn,
        int quantity,
        BigDecimal price) {
}