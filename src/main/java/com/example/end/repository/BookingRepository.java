package com.example.end.repository;

import com.example.end.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Методы для работы с бронированиями
}
