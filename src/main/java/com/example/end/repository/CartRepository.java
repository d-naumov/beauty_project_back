package com.example.end.repository;

import com.example.end.models.Cart;
import com.example.end.models.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {


}