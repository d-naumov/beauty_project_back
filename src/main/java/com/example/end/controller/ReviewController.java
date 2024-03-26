package com.example.end.controller;

import com.example.end.dto.ReviewDto;
import com.example.end.models.Review;
import com.example.end.service.interfaces.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "Get all reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @GetMapping("/master/{masterId}")
    @Operation(summary = "Get reviews by master")
    public ResponseEntity<List<ReviewDto>> getReviewsByMaster(@PathVariable Long masterId) {
        return new ResponseEntity<>(reviewService.getReviewsByMaster(masterId), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    @Operation(summary = "Add a review for a master")
    public ResponseEntity<ReviewDto> addReview(@PathVariable Long userId, @RequestBody ReviewDto reviewDto) {
        ReviewDto savedReviewDto = reviewService.addReview(userId, reviewDto);
        return new ResponseEntity<>(savedReviewDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "Delete a review")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/rating/{masterId}")
    @Operation(summary = "Get rating of a master")
    public ResponseEntity<Double> getMasterRating(@PathVariable Long masterId) {
        double rating = reviewService.getMasterRating(masterId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
}