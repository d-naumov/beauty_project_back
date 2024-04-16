package com.example.end.mapping;

import com.example.end.dto.BookingDto;
import com.example.end.dto.BookingUserDto;
import com.example.end.dto.NewBookingDto;
import com.example.end.models.Booking;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper {


    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUser().getId())
                .clientId(booking.getClient().getId())
                .masterId(booking.getMaster().getId())
                .procedureId(booking.getProcedure().getId())
                .dateTime(String.valueOf(booking.getDateTime()))
                .status(booking.getStatus())
                .build();

    }
    public BookingUserDto bookingUserToDto(Booking booking) {
        return BookingUserDto.builder()
                .id(booking.getId())
                .userId(booking.getUser().getId())
                .procedureId(booking.getProcedure().getId())
                .dateTime(String.valueOf(booking.getDateTime()))
                .status(booking.getStatus())
                .build();

    }



}