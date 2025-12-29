package com.spring.boot.project.ms.manga.store.domain.output;

import com.spring.boot.project.ms.manga.store.domain.model.cart.Cart;

public interface CartCachePortOut {
    Cart getCart(Long userId);

    void saveCart(Long userId, Cart cart);

    void deleteCart(Long userId);
}