package com.example.end.repository.interfaces;

import com.example.end.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Методы для работы с бронированиями
}
