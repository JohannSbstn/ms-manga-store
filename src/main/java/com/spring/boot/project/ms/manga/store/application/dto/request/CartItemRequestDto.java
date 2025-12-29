package com.spring.boot.project.ms.manga.store.application.dto.request;

import java.math.BigDecimal;

public record CartItemRequestDto(
        String isbn,
        int quantity,
        BigDecimal price
) {
}