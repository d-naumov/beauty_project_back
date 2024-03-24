package com.example.end.mapping;

import com.example.end.dto.CartDto;
import com.example.end.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class CartMapper {

    @Autowired
    private ProcedureMapper procedureMapper;

    public CartDto toDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .user(cart.getUser())
                .procedures(cart.getProcedures().stream()
                        .map(procedureMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
    public Cart toEntity(CartDto cartDTO){
        return Cart.builder()
                .id(cartDTO.getId())
                .user(cartDTO.getUser())
                .procedures(cartDTO.getProcedures().stream()
                        .map(procedureMapper::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}

