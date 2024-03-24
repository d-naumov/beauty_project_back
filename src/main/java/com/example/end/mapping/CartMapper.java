package com.example.end.mapping;

import com.example.end.dto.CartDto;
import com.example.end.models.Cart;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDto toDto(Cart cart);
    Cart toEntity(CartDto cartDto);
}

