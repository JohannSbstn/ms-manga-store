package com.spring.boot.project.ms.manga.store.infrastructure.input.rest;

import com.spring.boot.project.ms.manga.store.application.dto.request.CartItemRequestDto;
import com.spring.boot.project.ms.manga.store.application.dto.response.CartResponseDto;
import com.spring.boot.project.ms.manga.store.application.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<CartResponseDto> addItem(
            @PathVariable Long userId, @RequestBody @Valid CartItemRequestDto cartItemRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartService.addItem(userId, cartItemRequestDto));
    }

    @DeleteMapping("/{userId}/items/{isbn}")
    public ResponseEntity<CartResponseDto> removeItem(@PathVariable Long userId, @PathVariable String isbn) {
        return ResponseEntity.ok(cartService.removeItem(userId, isbn));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
