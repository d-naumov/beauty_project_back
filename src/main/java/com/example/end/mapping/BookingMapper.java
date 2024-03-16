package com.example.end.mapping;

import com.example.end.dto.BookingDto;
import com.example.end.models.Booking;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BookingMapper {

    BookingDto toDto(Booking booking);

    Booking toEntity(BookingDto bookingDTO);
}
