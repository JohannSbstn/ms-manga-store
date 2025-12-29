package com.spring.boot.project.ms.manga.store.application.dto.response;

import java.math.BigDecimal;

public record CartItemResponseDto(
        String isbn,
        int quantity,
        BigDecimal price,
        BigDecimal subtotal
) {
}
