package com.example.end.controller.api;

import com.example.end.dto.*;
import com.example.end.models.BookingStatus;
import com.example.end.models.User;
import com.example.end.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/bookings")
@Tags(value = {
        @Tag(name = "Bookings", description = "Handling of bookings")
})
@ApiResponses(value = {
        @ApiResponse(responseCode = "401",
                description = "User is not authorized",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = StandardResponseDto.class))),
        @ApiResponse(responseCode = "403",
                description = "Forbidden",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = StandardResponseDto.class)))
})
public interface BookingApi {
    @Operation(summary = "Create a booking", description = "Available to all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Booking was successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BookingDto createBooking(@RequestBody @Valid NewBookingDto bookingDto);

    @Operation(summary = "Update booking status", description = "Available to all users")
    @PutMapping("/status")
    void updateBookingStatus(@RequestBody @Valid BookingDto bookingDto);

    @Operation(summary = "Cancel booking", description = "Available to all users")
    @DeleteMapping("/{booking-id}")
    void cancelBooking(@Parameter(description = "booking identifier", example = "1")
                                       @PathVariable("booking-id") Long bookingId);


    @Operation(summary = "Find user bookings by it's status and user ID", description = "Available to all users")
    @GetMapping("/{user-id}")
    List<BookingDto> findBookingsByUser(@Parameter(description = "User ID", example = "1")
                                        @PathVariable("user-id") Long userId,
                                         @Parameter(description = "Status of the booking (CONFIRMED or COMPLETED)", example = "CONFIRMED")
                                         @RequestParam(name = "status", required = false) BookingStatus status);
}