package com.example.end.dto;

import com.example.end.models.BookingStatus;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private Long id;

    private Long userId;

    private Long procedureId;

    private LocalDateTime dateTime;

    private BookingStatus status;

}