package com.example.end.repository.interfaces;

import com.example.end.entity.Cart;
import com.example.end.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {


}