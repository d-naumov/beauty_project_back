package com.example.end.service.interfaces;

import com.example.end.dto.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(Long userId, Long procedureId);

    void updateBookingStatus(BookingDto bookingDto);

    void cancelBooking(Long bookingId);

    List<BookingDto> getUserBookings(Long userId);

    List<BookingDto> getMasterBookings(Long masterId);

    List<BookingDto> findActiveBookingsByUserId(Long userId);

    List<BookingDto> findCompletedBookingsByUserId(Long userId);
}
