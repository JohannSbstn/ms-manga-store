package com.spring.boot.project.ms.manga.store.domain.model.cart;

import java.util.List;

public record Cart(
        Long userId,
        List<CartItem> items
) {
}