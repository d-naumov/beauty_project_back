package com.example.end.controller;


import com.example.end.dto.BookingDto;
import com.example.end.dto.UserDto;

import com.example.end.service.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


//change
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("/create_booking")
    public ResponseEntity<BookingDto> createBooking(@RequestBody UserDto userDto, @RequestParam Long procedureId) {
        try {
            BookingDto createdBooking = bookingService.createBooking(userDto.getId(), procedureId);
            return ResponseEntity.ok(createdBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/master/{masterId}")
    public ResponseEntity<List<BookingDto>> getMasterBookings(@PathVariable Long masterId) {
        try {
            List<BookingDto> masterBookings = bookingService.getMasterBookings(masterId);
            return ResponseEntity.ok(masterBookings);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getUserBookings(@PathVariable Long userId) {
        List<BookingDto> userBookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(userBookings);
    }

    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        // Вызов метода сервиса для отмены бронирования
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking canceled successfully");
    }
    @GetMapping("/active/{userId}")
    public ResponseEntity<List<BookingDto>> getActiveBookingsByUserId(@PathVariable Long userId) {
        List<BookingDto> activeBookings = bookingService.findActiveBookingsByUserId(userId);
        return ResponseEntity.ok(activeBookings);
    }

    @GetMapping("/completed/{userId}")
    public ResponseEntity<List<BookingDto>> getCompletedBookingsByUserId(@PathVariable Long userId) {
        List<BookingDto> completedBookings = bookingService.findCompletedBookingsByUserId(userId);
        return ResponseEntity.ok(completedBookings);
    }

}