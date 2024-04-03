package com.example.end.mapping;

import com.example.end.dto.BookingDto;
import com.example.end.models.Booking;
import org.springframework.stereotype.Service;



@Service
public class BookingMapper {


    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .user(booking.getUser())
                .procedure(booking.getProcedure())
                .dateTime(booking.getDateTime())
                .status(booking.getStatus())
                .totalPrice(booking.getTotalPrice())
                .build();

    }

    public Booking toEntity(BookingDto bookingDTO) {
        return Booking.builder()
                .id(bookingDTO.getId())
                .user(bookingDTO.getUser())
                .procedure(bookingDTO.getProcedure())
                .dateTime(bookingDTO.getDateTime())
                .status(bookingDTO.getStatus())
                .totalPrice(bookingDTO.getTotalPrice())
                .build();
    }
}