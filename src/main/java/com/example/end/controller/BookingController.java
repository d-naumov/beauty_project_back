package com.example.end.controller;

import com.example.end.controller.api.BookingApi;
import com.example.end.dto.BookingDto;
import com.example.end.dto.BookingUserDto;
import com.example.end.dto.NewBookingDto;
import com.example.end.service.interfaces.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookingController implements BookingApi {

    private final BookingService bookingService;


    @Override
    public BookingDto createBooking(NewBookingDto bookingDto) {
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
    public List<BookingUserDto> getUserBookings(Long userId) {
        return bookingService.getUserBookings(userId);
    }

    @Override
    public List<BookingUserDto> getMasterBookings(Long masterId) {
        return bookingService.getMasterBookings(masterId);
    }


    @Override
    public List<BookingUserDto> findActiveBookingsByUserId(Long userId) {
        return bookingService.findActiveBookingsByUserId(userId);
    }

    @Override
    public List<BookingUserDto> findCompletedBookingsByUserId(Long userId) {
        return bookingService.findCompletedBookingsByUserId(userId);
    }

}