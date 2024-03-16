package com.example.end.controller;

import com.example.end.dto.BookingDto;
import com.example.end.models.Booking;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.example.end.service.interfaces.BookingService;
import com.example.end.service.interfaces.ProcedureService;
import com.example.end.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserServiceImpl userService;
    private final ProcedureService procedureService;

    @Autowired
    public BookingController(BookingService bookingService, UserServiceImpl userService, ProcedureService procedureService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.procedureService = procedureService;
    }


    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam String username, @RequestParam Long procedureId) {
        // Получение пользователя и процедуры...

        // Создание объекта BookingDto с информацией о бронировании
        BookingDto bookingDto = new BookingDto();
        bookingDto.setDateTime(LocalDateTime.now());

        // Получение пользователя по имени пользователя
        User user = userService.getUserByUsername(username);

        // Вызов метода createBooking
        if (user != null) {
            Booking newBooking = bookingService.createBooking(bookingDto, user.getId(), procedureId);
            return ResponseEntity.ok(newBooking);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/user/{username}")
    public ResponseEntity<List<Booking>> getUserBooking(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            List<Booking> bookings = bookingService.getUserBooking(user);
            return ResponseEntity.ok(bookings);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

