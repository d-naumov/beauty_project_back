package com.example.end.repository;


import com.example.end.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Дополнительные методы
}

