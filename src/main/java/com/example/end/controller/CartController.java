package com.example.end.controller;

import com.example.end.dto.CartDto;
import com.example.end.exceptions.EmptyCartException;
import com.example.end.exceptions.ProcedureNotFoundException;

import com.example.end.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;

    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable Long userId) {
        Optional<CartDto> cartDto = cartService.findCartDtoByUserId(userId);
        return ResponseEntity.of(cartDto);
    }

    @PostMapping("/{userId}/addProcedure/{procedureId}")
    public ResponseEntity<CartDto> addProcedureToCart(@PathVariable Long userId, @PathVariable Long procedureId) {
        try {
            CartDto updatedCartDto = cartService.addProcedureToCart(userId, procedureId);
            return ResponseEntity.ok(updatedCartDto);
        } catch (ProcedureNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/removeProcedure/{procedureId}")
    public ResponseEntity<CartDto> removeProcedureFromCart(@PathVariable Long userId, @PathVariable Long procedureId) {
        try {
            CartDto updatedCartDto = cartService.removeProcedureFromCart(userId, procedureId);
            return ResponseEntity.ok(updatedCartDto);
        } catch (ProcedureNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<String> checkoutCart(@PathVariable Long userId) {
        try {
            cartService.checkoutCart(userId);
            return ResponseEntity.ok("Cart checked out successfully");
        } catch (EmptyCartException e) {
            return ResponseEntity.badRequest().body("Cannot checkout an empty cart");
        }
    }
}
