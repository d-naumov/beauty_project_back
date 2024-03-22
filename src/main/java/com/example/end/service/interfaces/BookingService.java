package com.example.end.service.interfaces;

import com.example.end.dto.BookingDto;
import com.example.end.dto.UserDto;
import com.example.end.models.BookingStatus;
import com.example.end.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    BookingDto createBooking(BookingDto bookingDto, Long userId, Long procedureId);

    void updateBookingStatus(BookingDto bookingDto);

    void cancelBooking(Long bookingId);

    List<BookingDto> getUserBookings(Long userId);

    List<BookingDto> getMasterBookings(UserDto masterDto);
}


