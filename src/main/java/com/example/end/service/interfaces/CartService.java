package com.example.end.service.interfaces;

import com.example.end.dto.CartDto;
import com.example.end.exceptions.EmptyCartException;
import com.example.end.exceptions.ProcedureNotFoundException;
import com.example.end.models.Cart;

import java.util.Optional;

public interface CartService {



    void checkoutCart(Long userId);

}

