package com.example.end.controller.api;

import com.example.end.dto.BookingDto;
import com.example.end.dto.StandardResponseDto;
import com.example.end.dto.UserDto;
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
    BookingDto createBooking(@RequestBody @Valid BookingDto bookingDto);

    @Operation(summary = "Update booking status", description = "Available to all users")
    @PutMapping("/status")
    void updateBookingStatus(@RequestBody @Valid BookingDto bookingDto);

    @Operation(summary = "Cancel booking", description = "Available to all users")
    @DeleteMapping("/{booking-id}")
    void cancelBooking(@Parameter(description = "booking identifier", example = "1")
                                       @PathVariable("booking-id") Long bookingId);

    @Operation(summary = "Get user bookings", description = "Available to all users")
    @GetMapping("/user/{user-id}")
    List<BookingDto> getUserBookings(@Parameter(description = "user identifier", example = "1")
                                     @PathVariable("user-id") Long userId);

    @Operation(summary = "Get master bookings", description = "Available to all MASTERS")
    @GetMapping("/master/{master-id}")
    List<BookingDto> getMasterBookings(@Parameter(description = "master identifier", example = "1")
                                       @PathVariable("master-id") Long masterId);


    @Operation(summary = "Find active bookings by user ID", description = "Available to all users")
    @GetMapping("/active/{user-id}")
    List<BookingDto> findActiveBookingsByUserId(@Parameter(description = "user identifier", example = "1")
                                                @PathVariable("user-id") Long userId);

    @Operation(summary = "Find completed bookings by user ID", description = "Available to all users")
    @GetMapping("/completed/{user-id}")
    List<BookingDto> findCompletedBookingsByUserId(@Parameter(description = "user identifier", example = "1")
                                                   @PathVariable("user-id") Long userId);
}