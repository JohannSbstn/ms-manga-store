package com.spring.boot.project.ms.manga.store.application.mapper;

import com.spring.boot.project.ms.manga.store.application.dto.response.CartResponseDto;
import com.spring.boot.project.ms.manga.store.domain.model.cart.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = CartItemDtoMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartDtoMapper {

    @Mapping(target = "total", expression = "java(cart.totalPrice())")
    CartResponseDto toCartResponseDto(Cart cart);
}
