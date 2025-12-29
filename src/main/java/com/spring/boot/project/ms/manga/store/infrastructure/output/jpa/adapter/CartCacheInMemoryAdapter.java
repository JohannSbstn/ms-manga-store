package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.adapter;

import com.spring.boot.project.ms.manga.store.domain.model.cart.Cart;
import com.spring.boot.project.ms.manga.store.domain.output.CartCachePortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class CartCacheInMemoryAdapter implements CartCachePortOut {

    private final Map<Long, Cart> cache = new ConcurrentHashMap<>();

    @Override
    public Cart getCart(Long userId) {
        return cache.computeIfAbsent(
                userId,
                id -> new Cart(id, List.of())
        );
    }

    @Override
    public void saveCart(Long userId, Cart cart) {
        cache.put(userId, cart);
    }

    @Override
    public void deleteCart(Long userId) {
        cache.remove(userId);
    }
}
