package com.example.end.service;

import com.example.end.dto.BookingDto;
import com.example.end.dto.ProcedureDto;
import com.example.end.dto.UserDto;
import com.example.end.mapping.BookingMapper;
import com.example.end.mapping.ProcedureMapper;
import com.example.end.mapping.UserMapper;
import com.example.end.models.Booking;
import com.example.end.models.BookingStatus;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.example.end.repository.BookingRepository;
import com.example.end.repository.ProcedureRepository;
import com.example.end.service.interfaces.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserServiceImpl userService;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;
    private final ProcedureServiceImpl procedureService;
    private final ProcedureMapper procedureMapper;




    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        UserDto userDto = userService.getById(bookingDto.getUser().getId());
        User entity = userMapper.toEntity(userDto);

        ProcedureDto procedureDto = procedureService.findById(bookingDto.getProcedure().getId());
        Procedure procedure = procedureMapper.toEntity(procedureDto);

        if (entity != null) {
            Booking booking = new Booking();
            booking.setDateTime(bookingDto.getDateTime());
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
                    .map(booking -> {
                        double totalProcedurePrice = booking.getProcedures().stream()
                                .filter(procedure -> procedure.getUserMaster().contains(entity))
                                .mapToDouble(Procedure::getPrice)
                                .sum();

                        BookingDto bookingDto = bookingMapper.toDto(booking);
                        bookingDto.setTotalPrice(totalProcedurePrice);
                        return bookingDto;
                    })
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("User is not a master");
        }
    }

    public boolean isMaster(UserDto userDto) {
        User entity = userMapper.toEntity(userDto);
        return entity.getRole() == User.Role.MASTER;
    }

    @Override
    public List<BookingDto> findActiveBookingsByUserId(Long userId) {
        List<Booking> activeBookings = bookingRepository.findActiveBookingsByUserId(userId);
        return activeBookings.stream().map(bookingMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> findCompletedBookingsByUserId(Long userId) {
        List<Booking> completedBookings = bookingRepository.findCompletedBookingsByUserId(userId);
        return completedBookings.stream().map(bookingMapper::toDto).collect(Collectors.toList());
    }
}