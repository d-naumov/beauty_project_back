package com.example.end.controller;

import com.example.end.controller.api.BookingApi;
import com.example.end.dto.BookingDto;
import com.example.end.dto.UserDto;
import com.example.end.service.interfaces.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookingController implements BookingApi {

    private final BookingService bookingService;


    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        return bookingService.createBooking(bookingDto);
    }

    @Override
    public void updateBookingStatus(BookingDto bookingDto) {
        bookingService.updateBookingStatus(bookingDto);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }

    @Override
    public List<BookingDto> getUserBookings(Long userId) {
        return bookingService.getUserBookings(userId);
    }

    @Override
    public List<BookingDto> getMasterBookings(Long masterId) {
        return bookingService.getMasterBookings(masterId);
    }


    @Override
    public List<BookingDto> findActiveBookingsByUserId(Long userId) {
        return bookingService.findActiveBookingsByUserId(userId);
    }

    @Override
    public List<BookingDto> findCompletedBookingsByUserId(Long userId) {
        return bookingService.findCompletedBookingsByUserId(userId);
    }
}