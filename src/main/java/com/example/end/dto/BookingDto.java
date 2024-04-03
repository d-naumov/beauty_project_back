package com.example.end.dto;

import com.example.end.models.BookingStatus;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO representing a booking")
public class BookingDto {

    @Schema(description = "Unique identifier of the booking", example = "1")
    private Long id;

    @NotNull(message = "User ID cannot be null")
    @Schema(description = "User ID associated with the booking", example = "1")
    private User user;

    @NotNull(message = "Procedure ID cannot be null")
    @Schema(description = "Procedure ID associated with the booking", example = "Haircut")
    private Procedure procedure;

    @NotNull(message = "Date and time cannot be null")
    @Schema(description = "Date and time of the booking", example = "2024-03-16T10:00:00")
    private LocalDateTime dateTime;

    @NotNull(message = "Status cannot be null")
    @Schema(description = "Status of the booking")
    private BookingStatus status;

    @Min(value = 0, message = "Total price cannot be less than 0")
    @Schema(description = "Total price of the booking")
    private double totalPrice;
}
