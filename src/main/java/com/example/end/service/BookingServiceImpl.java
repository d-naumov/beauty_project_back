package com.example.end.service;


import com.example.end.dto.BookingDto;
import com.example.end.models.Booking;
import com.example.end.models.BookingStatus;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.example.end.repository.BookingRepository;
import com.example.end.service.interfaces.BookingService;
import com.example.end.service.interfaces.ProcedureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserServiceImpl userService;
    private final ProcedureService procedureService;

    public BookingServiceImpl(BookingRepository bookingRepository, UserServiceImpl userService,
                              ProcedureService procedureService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.procedureService = procedureService;
    }


    @Override
    public Booking createBooking(BookingDto bookingDto, int userId, int procedureId) {
        Optional<User> userOptional = userService.findById(userId);
        Optional<Procedure> procedureOptional = Optional.ofNullable(procedureService.findById(procedureId));

        if (userOptional.isPresent() && procedureOptional.isPresent()) {
            User user = userOptional.get();
            Procedure procedure = procedureOptional.get();

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setProcedure(procedure);
            booking.setDateTime(bookingDto.getDateTime());
            booking.setStatus(BookingStatus.PENDING);

            return bookingRepository.save(booking);
        } else {
            // Обработка ошибки, если пользователь или процедура не найдены
            return null;
        }
    }


    @Override
    public void updateBookingStatus(int bookingId, BookingStatus status) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

        bookingOptional.ifPresent(booking -> {
            booking.setStatus(status);
            bookingRepository.save(booking);
        });
    }


    @Override
    public List<Booking> getBookingForUser(User user) {
        return null;
    }

    @Override
    public Booking createBooking(User user, Procedure procedure) {
        return null;
    }
}