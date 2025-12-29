package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.input.CartPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.cart.Cart;
import com.spring.boot.project.ms.manga.store.domain.model.cart.CartItem;
import com.spring.boot.project.ms.manga.store.domain.output.CartCachePortOut;

public class CartUseCase implements CartPortIn {

    private final CartCachePortOut cartCachePortOut;

    public CartUseCase(CartCachePortOut cartCachePortOut) {
        this.cartCachePortOut = cartCachePortOut;
    }

    @Override
    public Cart getCart(Long userId) {
        return cartCachePortOut.getCart(userId);
    }

    @Override
    public Cart addItem(Long userId, CartItem item) {
        Cart updated = cartCachePortOut.getCart(userId).addItem(item);
        cartCachePortOut.saveCart(userId, updated);
        return updated;
    }

    @Override
    public Cart removeItem(Long userId, String isbn) {
        Cart updated = cartCachePortOut.getCart(userId).removeItem(isbn);
        cartCachePortOut.saveCart(userId, updated);
        return updated;
    }

    @Override
    public void clearCart(Long userId) {
        cartCachePortOut.deleteCart(userId);
    }
}
