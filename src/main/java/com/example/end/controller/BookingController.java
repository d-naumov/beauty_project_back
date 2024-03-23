package com.example.end.controller;


import com.example.end.dto.BookingDto;
import com.example.end.dto.UserDto;
import com.example.end.mapping.UserMapper;
import com.example.end.models.User;
import com.example.end.service.interfaces.BookingService;
import com.example.end.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @Autowired
    public BookingController(BookingService bookingService, UserServiceImpl userService, UserMapper userMapper) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.userMapper = userMapper;
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

}