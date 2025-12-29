package com.spring.boot.project.ms.manga.store.application.service;

import com.spring.boot.project.ms.manga.store.application.dto.request.CartItemRequestDto;
import com.spring.boot.project.ms.manga.store.application.dto.response.CartResponseDto;

public interface CartService {
    CartResponseDto getCart(Long userId);

    CartResponseDto addItem(Long userId, CartItemRequestDto item);

    CartResponseDto removeItem(Long userId, String isbn);

    void clearCart(Long userId);
}
