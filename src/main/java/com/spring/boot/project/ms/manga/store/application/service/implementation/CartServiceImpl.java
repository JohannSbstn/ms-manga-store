package com.spring.boot.project.ms.manga.store.application.service.implementation;

import com.spring.boot.project.ms.manga.store.application.dto.request.CartItemRequestDto;
import com.spring.boot.project.ms.manga.store.application.dto.response.CartResponseDto;
import com.spring.boot.project.ms.manga.store.application.mapper.CartDtoMapper;
import com.spring.boot.project.ms.manga.store.application.mapper.CartItemDtoMapper;
import com.spring.boot.project.ms.manga.store.application.service.CartService;
import com.spring.boot.project.ms.manga.store.domain.input.CartPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartPortIn cartPortIn;
    private final CartDtoMapper cartDtoMapper;
    private final CartItemDtoMapper cartItemDtoMapper;

    @Override
    public CartResponseDto getCart(Long userId) {
        return cartDtoMapper.toCartResponseDto(
                cartPortIn.getCart(userId)
        );
    }

    @Override
    public CartResponseDto addItem(Long userId, CartItemRequestDto item) {
        return cartDtoMapper.toCartResponseDto(
                cartPortIn.addItem(userId, cartItemDtoMapper.toDomain(item))
        );
    }

    @Override
    public CartResponseDto removeItem(Long userId, String isbn) {
        return cartDtoMapper.toCartResponseDto(
                cartPortIn.removeItem(userId, isbn)
        );
    }

    @Override
    public void clearCart(Long userId) {
        cartPortIn.clearCart(userId);
    }
}
