package com.spring.boot.project.ms.manga.store.domain.input;

import com.spring.boot.project.ms.manga.store.domain.model.cart.Cart;
import com.spring.boot.project.ms.manga.store.domain.model.cart.CartItem;

public interface CartPortIn {
    Cart getCart(Long userId);

    Cart addItem(Long userId, CartItem item);

    Cart removeItem(Long userId, String isbn);

    void clearCart(Long userId);
}
