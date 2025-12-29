package com.spring.boot.project.ms.manga.store.domain.model.cart;

import java.math.BigDecimal;
import java.util.List;

public record Cart(
        Long userId,
        List<CartItem> items
) {
    public Cart addItem(CartItem item) {
        List<CartItem> updatedItems = items.stream()
                .filter(i -> !i.isbn().equals(item.isbn()))
                .toList();

        int quantity = items.stream()
                .filter(i -> i.isbn().equals(item.isbn()))
                .mapToInt(CartItem::quantity)
                .sum() + item.quantity();

        updatedItems = new java.util.ArrayList<>(updatedItems);
        updatedItems.add(new CartItem(item.isbn(), quantity, item.price()));

        return new Cart(userId, updatedItems);
    }

    public Cart removeItem(String isbn) {
        return new Cart(
                userId,
                items.stream()
                        .filter(i -> !i.isbn().equals(isbn))
                        .toList()
        );
    }

    public BigDecimal totalPrice() {
        return items.stream()
                .map(item ->
                        item.price()
                                .multiply(BigDecimal.valueOf(item.quantity()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}