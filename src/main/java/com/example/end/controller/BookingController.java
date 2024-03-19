package com.example.end.controller;

import com.example.end.dto.BookingDto;
import com.example.end.models.BookingStatus;
import com.example.end.models.User;
import com.example.end.service.interfaces.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping

    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto, @RequestParam Long userId, @RequestParam Long procedureId) {
        BookingDto newBooking = bookingService.createBooking(bookingDto, userId, procedureId);
        if (newBooking == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(newBooking);
    }

    @PutMapping("/{bookingId}")
    @Operation(summary = "bookingId", description = "blabla")
    public ResponseEntity<Void> updateBookingStatus(@PathVariable Long bookingId, @RequestParam BookingStatus status) {
        bookingService.updateBookingStatus(bookingId, status);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/user/{username}")
    public ResponseEntity<List<BookingDto>> getUserBooking(@PathVariable String username) {
        User user = new User();
        List<BookingDto> userBookings = bookingService.getUserBooking(user);
        return ResponseEntity.ok(userBookings);
    }
}

