package com.example.end.service.interfaces;

import com.example.end.models.Cart;

import java.util.Optional;

public interface CartService {
    Cart addProcedureToCart(Long userId, Long procedureId);

    Cart removeProcedureFromCart(Long userId, Long procedureId);

    void checkoutCart(Long userId);

    Optional<Cart> findByUserId(Long userId);

}

