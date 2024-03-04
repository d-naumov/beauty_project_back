package com.example.end.repository.interfaces;


import com.example.end.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    // Дополнительные методы
}

