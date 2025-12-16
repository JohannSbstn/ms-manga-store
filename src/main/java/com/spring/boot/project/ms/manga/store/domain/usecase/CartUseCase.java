package com.spring.boot.project.ms.manga.store.domain.usecase;

import com.spring.boot.project.ms.manga.store.domain.input.CartPortIn;
import com.spring.boot.project.ms.manga.store.domain.model.cart.Cart;
import com.spring.boot.project.ms.manga.store.domain.model.cart.CartItem;
import com.spring.boot.project.ms.manga.store.domain.output.CartCachePortOut;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartUseCase implements CartPortIn {

    private final CartCachePortOut cartCachePortOut;

    public CartUseCase(CartCachePortOut cartCachePortOut) {
        this.cartCachePortOut = cartCachePortOut;
    }

    @Override
    public Optional<Cart> getCart(Long userId) {
        return cartCachePortOut.getCart(userId)
                .or(() -> Optional.of(new Cart(userId, List.of())));
    }

    @Override
    public Cart addItem(Long userId, CartItem item) {
        Cart current = cartCachePortOut.getCart(userId)
                .orElseGet(() -> new Cart(userId, List.of()));

        List<CartItem> newItems = new ArrayList<>(current.items());
        newItems.add(item);

        Cart updated = new Cart(userId, newItems);

        cartCachePortOut.saveCart(userId, updated);

        return updated;
    }

    @Override
    public Cart removeItem(Long userId, String isbn) {
        Cart current = cartCachePortOut.getCart(userId)
                .orElseGet(() -> new Cart(userId, List.of()));

        List<CartItem> newItems = current.items().stream()
                .filter(i -> !i.isbn().equals(isbn))
                .toList();

        Cart updated = new Cart(userId, newItems);

        cartCachePortOut.saveCart(userId, updated);

        return updated;
    }

    @Override
    public void clearCart(Long userId) {
        cartCachePortOut.deleteCart(userId);
    }
}
