package com.example.end.controller;

import com.example.end.models.Review;
import com.example.end.service.interfaces.ReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.end.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/reviews")

public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")

    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> reviewOptional = reviewService.getReviewById(id);
        return reviewOptional.map(review -> ResponseEntity.ok().body(review))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping

    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @PostMapping

    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewDto reviewDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Review createdReview = reviewService.createReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PutMapping("/{id}")

    public ResponseEntity<Review> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Review> updatedReview = reviewService.updateReview(id, reviewDto);
        return updatedReview.map(review -> ResponseEntity.ok().body(review))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Long id) {
        if (reviewService.deleteReview(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

