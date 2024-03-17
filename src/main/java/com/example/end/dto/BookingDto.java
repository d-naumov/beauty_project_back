package com.example.end.dto;

import com.example.end.models.BookingStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO representing a booking")
public class BookingDto {

    @Schema(description = "Unique identifier of the booking", example = "1")
    private Long id;

    @Schema(description = "User ID associated with the booking", example = "123")
    private Long userId;

    @Schema(description = "Procedure ID associated with the booking", example = "456")
    private Long procedureId;

    @Schema(description = "Date and time of the booking", example = "2024-03-16T10:00:00")
    private LocalDateTime dateTime;

    @Schema(description = "Status of the booking")
    private BookingStatus status;

}