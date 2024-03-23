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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
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
    public BookingDto createBooking(Long userId, Long procedureId) {
        UserDto userDto = userService.getById(userId);
        Procedure procedure = procedureService.findById(procedureId);

        if (userDto != null && procedure != null) {
            User entity = userMapper.toEntity(userDto);
            Booking booking = new Booking();
            booking.setDateTime(LocalDateTime.now());
            booking.setUser(entity);
            booking.setProcedure(procedure);
            booking = bookingRepository.save(booking);
            return bookingMapper.toDto(booking);
        } else {
            throw new RuntimeException("User or Procedure not found");
        }
    }



    @Override
    public void updateBookingStatus(BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        Booking existingBooking = bookingRepository.findById(booking.getId())
                .orElseThrow(() -> new IllegalArgumentException("Booking with ID " + booking.getId() + " not found"));
        existingBooking.setStatus(booking.getStatus());
        bookingRepository.save(existingBooking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(bookingId);
        bookingDto.setStatus(BookingStatus.CANCELED);
        updateBookingStatus(bookingDto);
    }

    @Override
    public List<BookingDto> getUserBookings(Long userId) {
        UserDto userDto = userService.getById(userId);
        if (userDto != null) {
            User entity = userMapper.toEntity(userDto);
            List<Booking> bookings = bookingRepository.findByUser(entity);
            return bookings.stream()
                    .map(bookingMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<BookingDto> getMasterBookings(Long masterId) {
        UserDto masterDto = userService.getById(masterId);
        if (masterDto != null && isMaster(masterDto)) {
            User entity = userMapper.toEntity(masterDto);
            List<Booking> bookings = bookingRepository.findByUser(entity);
            return bookings.stream()
                    .map(booking -> new BookingDto(
                            booking.getId(),
                            booking.getUser().getId(),
                            booking.getProcedure().getId(),
                            booking.getDateTime(),
                            booking.getStatus()))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("User is not a master");
        }
    }
    private boolean isMaster(UserDto userDto) {
        User entity = userMapper.toEntity(userDto);
        return entity.getRole() == User.Role.MASTER;
    }
}