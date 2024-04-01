package com.example.end.repository;

import com.example.end.models.Booking;
import com.example.end.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User userDto);

    List<Booking> findActiveBookingsByUserId(Long userId);

    List<Booking> findCompletedBookingsByUserId(Long userId);

}