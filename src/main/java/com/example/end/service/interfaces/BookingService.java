package com.example.end.service.interfaces;

import com.example.end.dto.BookingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    BookingDto createBooking(Long userId, Long procedureId);

    void updateBookingStatus(BookingDto bookingDto);

    void cancelBooking(Long bookingId);

    List<BookingDto> getUserBookings(Long userId);

    List<BookingDto> getMasterBookings(Long masterDto);
}

    List<BookingDto> findActiveBookingsByUserId(Long userId);

    List<BookingDto> findCompletedBookingsByUserId(Long userId);
}


