package com.example.end.service;

import com.example.end.dto.*;
import com.example.end.exceptions.ProcedureNotFoundException;
import com.example.end.exceptions.UserNotFoundException;
import com.example.end.mapping.BookingMapper;
import com.example.end.mapping.ProcedureMapper;
import com.example.end.mapping.UserMapper;
import com.example.end.models.Booking;
import com.example.end.models.BookingStatus;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.example.end.repository.BookingRepository;
import com.example.end.repository.ProcedureRepository;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl  implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserServiceImpl userService;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;
    private final ProcedureRepository procedureRepository;
    private final UserRepository userRepository;



    @Override
    public BookingDto createBooking(NewBookingDto bookingDto) {
        User client = userRepository.findById(bookingDto.getClientId())
                .orElseThrow(() -> new UserNotFoundException("Client not found"));

        User master = userRepository.findById(bookingDto.getMasterId())
                .orElseThrow(() -> new UserNotFoundException("Master not found"));

        Procedure procedure = procedureRepository.findById(bookingDto.getProcedureId())
                .orElseThrow(() -> new ProcedureNotFoundException("Procedure not found"));

        Booking booking = new Booking();
        booking.setDateTime(LocalDateTime.parse(bookingDto.getDateTime()));
        booking.setClient(client);
        booking.setMaster(master);
        booking.setProcedure(procedure);
        booking.setStatus(BookingStatus.CONFIRMED);

        booking = bookingRepository.save(booking);

        return bookingMapper.toDto(booking);
    }

    @Override
    public void updateBookingStatus(BookingDto bookingDto) {
        Booking existingBooking = bookingRepository.findById(bookingDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Booking with ID " + bookingDto.getId() + " not found"));

        existingBooking.setStatus(bookingDto.getStatus());
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
    public List<BookingUserDto> getUserBookings(Long userId) {
        UserDto userDto = userService.getById(userId);
        if (userDto != null) {
            User entity = userMapper.toEntity(userDto);
            List<Booking> bookings = bookingRepository.findByUser(entity);
            return bookings.stream()
                    .map(bookingMapper::bookingUserToDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public List<BookingUserDto> getMasterBookings(Long masterId) {
        UserDto masterDto = userService.getById(masterId);
        if (masterDto != null && isMaster(masterDto)) {
            User entity = userMapper.toEntity(masterDto);
            List<Booking> bookings = bookingRepository.findByUser(entity);
            return bookings.stream()
                    .map(bookingMapper::bookingUserToDto)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("User is not a master");
        }
    }

    @Override
    public boolean isMaster(UserDto userDto) {
        User entity = userMapper.toEntity(userDto);
        return entity.getRole() == User.Role.MASTER;
    }

    @Override
    public List<BookingUserDto> findActiveBookingsByUserId(Long userId) {
        List<Booking> activeBookings = bookingRepository.findActiveBookingsByUserId(userId);
        return activeBookings.stream().filter(Objects::nonNull).map(bookingMapper::bookingUserToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingUserDto> findCompletedBookingsByUserId(Long userId) {
        List<Booking> completedBookings = bookingRepository.findCompletedBookingsByUserId(userId);
        return completedBookings.stream().map(bookingMapper::bookingUserToDto).collect(Collectors.toList());
    }
}