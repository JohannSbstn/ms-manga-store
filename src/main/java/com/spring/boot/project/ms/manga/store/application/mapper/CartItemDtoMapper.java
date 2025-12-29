package com.spring.boot.project.ms.manga.store.application.mapper;

import com.spring.boot.project.ms.manga.store.application.dto.request.CartItemRequestDto;
import com.spring.boot.project.ms.manga.store.application.dto.response.CartItemResponseDto;
import com.spring.boot.project.ms.manga.store.domain.model.cart.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartItemDtoMapper {

    @Mapping(target = "subtotal",
            expression = "java(item.price().multiply(java.math.BigDecimal.valueOf(item.quantity())))")
    CartItemResponseDto toResponse(CartItem item);

    CartItem toDomain(CartItemRequestDto dto);
}
