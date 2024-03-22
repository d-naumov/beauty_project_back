package com.example.end.service;


import com.example.end.dto.BookingDto;
import com.example.end.dto.UserDto;
import com.example.end.mapping.BookingMapper;
import com.example.end.mapping.UserMapper;
import com.example.end.models.Booking;
import com.example.end.models.BookingStatus;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.example.end.repository.BookingRepository;
import com.example.end.service.interfaces.BookingService;
import com.example.end.service.interfaces.ProcedureService;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserServiceImpl userService;
    private final ProcedureService procedureService;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;


    public BookingServiceImpl(BookingRepository bookingRepository, UserServiceImpl userService,
                              ProcedureService procedureService, BookingMapper bookingMapper, UserMapper userMapper) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.procedureService = procedureService;
        this.bookingMapper = bookingMapper;
        this.userMapper = userMapper;
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


    @Override
    public void updateBookingStatus(BookingDto bookingDto) {
        // Преобразуйте BookingDto в объект Booking
        Booking booking = bookingMapper.toEntity(bookingDto);

        // Проверьте существование бронирования по его идентификатору
        Optional<Booking> existingBookingOptional = bookingRepository.findById(booking.getId());
        if (existingBookingOptional.isPresent()) {
            // Обновите статус бронирования
            Booking existingBooking = existingBookingOptional.get();
            existingBooking.setStatus(booking.getStatus());
            bookingRepository.save(existingBooking);
        } else {
            throw new IllegalArgumentException("Booking with ID " + booking.getId() + " not found");
        }
    }
    @Override
    public void cancelBooking(Long bookingId) {
        // Создаем объект BookingDto для передачи идентификатора бронирования и статуса
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(bookingId); // Устанавливаем идентификатор бронирования
        bookingDto.setStatus(BookingStatus.CANCELED); // Устанавливаем статус "CANCELED"

        // Вызываем метод updateBookingStatus с объектом BookingDto
        updateBookingStatus(bookingDto);
    }


    @Override
    public List<BookingDto> getUserBookings(Long userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto = userMapper.toDto(user);
            List<Booking> bookings = bookingRepository.findByUser(userDto);
            return bookings.stream()
                    .map(bookingMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
    @Override
    public List<BookingDto> getMasterBookings(UserDto masterDto) {
        // Проверяем, является ли переданный пользователь мастером
        if (isMaster(masterDto)) {
            // Получаем список бронирований для данного мастера
            List<Booking> bookings = bookingRepository.findByUser(masterDto);

            // Преобразуем список бронирований в список BookingDto
            return bookings.stream()
                    .map(booking -> new BookingDto(
                            booking.getId(),
                            booking.getUser().getId(), // ID пользователя
                            booking.getProcedure().getId(), // ID процедуры
                            booking.getDateTime(), // Дата и время бронирования
                            booking.getStatus())) // Статус бронирования
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("User is not a master");
        }
    }



    // Метод для проверки, является ли пользователь мастером
    private boolean isMaster(UserDto userDto) {
        // Преобразуем объект UserDto в объект User
        User user = userMapper.toEntity(userDto);

        // Проверяем роль пользователя
        return user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("MASTER"));
    }
}