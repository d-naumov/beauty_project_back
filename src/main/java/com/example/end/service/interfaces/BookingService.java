package com.example.end.service.interfaces;

import com.example.end.dto.BookingDto;
import com.example.end.models.Booking;
import com.example.end.models.BookingStatus;
import com.example.end.models.Procedure;
import com.example.end.models.User;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingDto bookingDto, int userId, int procedureId);

    void updateBookingStatus(int bookingId, BookingStatus status);

    List<Booking> getBookingForUser(User user);

    Booking createBooking(User user, Procedure procedure);

    // Методы для управления бронированиями
}
