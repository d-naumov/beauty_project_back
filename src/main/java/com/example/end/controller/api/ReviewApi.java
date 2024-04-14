package com.example.end.controller.api;

import com.example.end.dto.StandardResponseDto;
import com.example.end.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import com.example.end.dto.ReviewDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;
@RequestMapping("/api/reviews")
@Tags(value = {
        @Tag(name = "Reviews", description = "Operations related to reviews")
})
@ApiResponses(value = {
        @ApiResponse(responseCode = "401",
                description = "User is not authenticated",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = StandardResponseDto.class))),
        @ApiResponse(responseCode = "403",
                description = "Forbidden",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = StandardResponseDto.class)))})
public interface ReviewApi {

    @Operation(summary = "Get all reviews", description = "Available to ADMIN")
    @GetMapping
    List<ReviewDto> getAllReviews();

    @Operation(summary = "Get reviews by master", description = "Available to all users")
    @GetMapping("/master/{masterId}")
    List<ReviewDto> getReviewsByMaster(@Parameter(description = "ID of the master to filter reviews.")
                                                       @PathVariable Long masterId);

    @Operation(summary = "Add a review for a master", description = "Available to CLIENT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Review was added successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ReviewDto addReview(@RequestBody @Valid ReviewDto reviewDto);

    @Operation(summary = "Delete a review", description = "Available to ADMIN")
    @DeleteMapping("/{reviewId}")
    void deleteReview(@Parameter(description = "ID of the review to be deleted.")
                                      @PathVariable ("reviewId") Long reviewId);

    @Operation(summary = "Get rating of a master", description = "Available to all users")
    @GetMapping
            ("/rating/{masterId}")
    double getMasterRating(@Parameter(description = "ID of the master to get rating.")
                                           @PathVariable ("masterId") Long masterId);
}