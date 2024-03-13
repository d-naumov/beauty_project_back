package com.example.end.controller;

import com.example.end.models.Booking;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.example.end.service.UserServiceImpl;
import com.example.end.service.interfaces.BookingService;
import com.example.end.service.interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Booking> createBooking(@RequestParam String username, @RequestParam int procedureId) {
        // метод в BookingService для создания записи
        User user = userService.getUserByUsername(username);
        Optional<Procedure> procedureOptional = Optional.ofNullable(procedureService.getProcedureById(procedureId));

        if (user != null && procedureOptional.isPresent()) {
            Procedure procedure = procedureOptional.get(); // Unwrap the Optional Разверните необязательный
            Booking newBooking = bookingService.createBooking(user, procedure);
            return ResponseEntity.ok(newBooking);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/user/{username}")
    public ResponseEntity<List<Booking>> getAppointmentsForUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            List<Booking> bookings = bookingService.getBookingForUser(user);
            return ResponseEntity.ok(bookings);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}


