package com.spring.boot.project.ms.manga.store.domain.output;

import com.spring.boot.project.ms.manga.store.domain.model.cart.Cart;

import java.util.Optional;

public interface CartCachePortOut {
    Optional<Cart> getCart(Long userId);

    void saveCart(Long userId, Cart cart);

    void deleteCart(Long userId);
}