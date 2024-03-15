package com.example.end.controller;

import com.example.end.exceptions.EmptyCartException;
import com.example.end.exceptions.ProcedureNotFoundException;
import com.example.end.models.Cart;
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
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        Optional<Cart> cart = cartService.findByUserId(userId);
        return ResponseEntity.of(cart);
    }

    @PostMapping("/{userId}/addProcedure/{procedureId}")
    public ResponseEntity<Cart> addProcedureToCart(@PathVariable Long userId, @PathVariable Long procedureId) {
        try {
            Cart updatedCart = cartService.addProcedureToCart(userId, procedureId);
            return ResponseEntity.ok(updatedCart);
        } catch (ProcedureNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/removeProcedure/{procedureId}")
    public ResponseEntity<Cart> removeProcedureFromCart(@PathVariable Long userId, @PathVariable Long procedureId) {
        try {
            Cart updatedCart = cartService.removeProcedureFromCart(userId, procedureId);
            return ResponseEntity.ok(updatedCart);
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

