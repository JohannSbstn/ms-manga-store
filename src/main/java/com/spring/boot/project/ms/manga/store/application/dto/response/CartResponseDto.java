package com.spring.boot.project.ms.manga.store.application.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record CartResponseDto(
        Long userId,
        List<CartItemResponseDto> items,
        BigDecimal total
) {
}
