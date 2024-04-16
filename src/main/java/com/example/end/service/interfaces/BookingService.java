package com.example.end.service.interfaces;

import com.example.end.dto.BookingDto;
import com.example.end.dto.BookingUserDto;
import com.example.end.dto.NewBookingDto;
import com.example.end.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    BookingDto createBooking(NewBookingDto bookingDto);

    void updateBookingStatus(BookingDto bookingDto);

    void cancelBooking(Long bookingId);

    List<BookingUserDto> getUserBookings(Long userId);

    List<BookingUserDto> getMasterBookings(Long masterId);

    boolean isMaster(UserDto userDto);

    List<BookingUserDto> findActiveBookingsByUserId(Long userId);

    List<BookingUserDto> findCompletedBookingsByUserId(Long userId);
}
