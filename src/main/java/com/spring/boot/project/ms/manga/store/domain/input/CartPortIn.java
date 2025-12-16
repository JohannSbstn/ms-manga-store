package com.spring.boot.project.ms.manga.store.domain.input;

import com.spring.boot.project.ms.manga.store.domain.model.cart.Cart;
import com.spring.boot.project.ms.manga.store.domain.model.cart.CartItem;

import java.util.Optional;

public interface CartPortIn {
    Optional<Cart> getCart(Long userId);

    Cart addItem(Long userId, CartItem item);

    Cart removeItem(Long userId, String isbn);

    void clearCart(Long userId);
}
