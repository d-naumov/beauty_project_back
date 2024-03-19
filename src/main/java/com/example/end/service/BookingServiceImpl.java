package com.example.end.service;

import com.example.end.dto.BookingDto;
import com.example.end.mapping.BookingMapper;
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

    private  BookingRepository bookingRepository;
    private  UserServiceImpl userService;
    private  BookingMapper bookingMapper;
    private  ProcedureService procedureService;

    public BookingServiceImpl(BookingRepository bookingRepository, UserServiceImpl userService, BookingMapper bookingMapper,
                              ProcedureService procedureService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.bookingMapper = bookingMapper;
        this.procedureService = procedureService;
    }
    @Override
    public BookingDto createBooking(BookingDto bookingDto, Long userId, Long procedureId) {
        Optional<User> userOptional = userService.findById(userId);
        Optional<Procedure> procedureOptional = Optional.ofNullable(procedureService.findById(procedureId));

        if (userOptional.isPresent() && procedureOptional.isPresent()) {
            User user = userOptional.get();
            Procedure procedure = procedureOptional.get();

            // Преобразование BookingDto в Booking с помощью маппера
            Booking booking = bookingMapper.toEntity(bookingDto);

            // Установка пользователя и процедуры для бронирования
            booking.setUser(user);
            booking.setProcedure(procedure);

            // Сохранение бронирования в базе данных
            booking = bookingRepository.save(booking);

            // Преобразование сохраненного бронирования обратно в BookingDto
            return bookingMapper.toDto(booking);
        } else {
            throw new RuntimeException("User or Procedure not found");
        }
    }

//    @Override
//    public BookingDto createBooking(BookingDto bookingDto, Long userId, Long procedureId) {
//        Optional<User> userOptional = userService.findById(userId);
//        Optional<Procedure> procedureOptional = Optional.ofNullable(procedureService.findById(procedureId));
//
//        if (userOptional.isPresent() && procedureOptional.isPresent()) {
//            User user = userOptional.get();
//            Procedure procedure = procedureOptional.get();
//            Booking booking = new Booking();
//            booking.setUser(user);
//            booking.setProcedure(procedure);
//            booking.setDateTime(bookingDto.getDateTime());
//            booking.setStatus(BookingStatus.PENDING);
//            booking = bookingRepository.save(booking);
//            return bookingMapper.toDto(booking);
//        } else {
//            return null;
//        }
//    }

    @Override
    public void updateBookingStatus(Long bookingId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking with id " + bookingId + " not found"));
        if (status != null) {
            booking.setStatus(status);
        }

        bookingRepository.save(booking);
    }

    @Override
    public List<BookingDto> getUserBooking(User user) {
        List<Booking> bookings = bookingRepository.findByUser(user);
        return bookings.stream()
                .map(bookingMapper::toDto)
                .toList();
    }



//    @Override
//    public BookingDto createBooking(User user, Procedure procedure) {
//        //  создания нового бронирования для пользователя и процедуры
//        // Например, создайте новый объект Booking, установите необходимые значения и сохраните его
//        BookingDto booking = new BookingDto();
//        booking.setUser(user);
//        booking.setProcedure(procedure);
//        // Установите другие свойства бронирования, такие как дата и статус
//        booking.setDateTime(LocalDateTime.now());
//        booking.setStatus(BookingStatus.PENDING);
//
//        // Сохраните новое бронирование
//        return bookingRepository.save(booking);
//    }

}
