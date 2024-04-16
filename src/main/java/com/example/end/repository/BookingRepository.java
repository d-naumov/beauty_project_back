package com.example.end.repository;

import com.example.end.models.Booking;
import com.example.end.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User userDto);
    List<Booking> findCompletedBookingsByClient_Id(Long clientId);

    List<Booking> findCompletedBookingsByMaster_Id(Long masterId);

    List<Booking> findActiveBookingsByClient_Id(Long clientId);

    List<Booking> findActiveBookingsByMaster_Id(Long masterId);

    List<Booking> findActiveBookingsByUserId(Long userId);

    List<Booking> findCompletedBookingsByUserId(Long userId);
}