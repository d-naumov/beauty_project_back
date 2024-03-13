package com.example.end.controller;

import com.example.end.dto.ReviewDto;
import com.example.end.models.Review;
import com.example.end.service.interfaces.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@Api(tags = "Reviews", description = "Operations related to reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get review by ID", response = Review.class)
    public ResponseEntity<Review> getReviewById(@PathVariable int id) {
        Optional<Review> reviewOptional = reviewService.getReviewById(id);
        return reviewOptional.map(review -> ResponseEntity.ok().body(review))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @ApiOperation(value = "Get all reviews", response = List.class)
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    @ApiOperation(value = "Create a new review", response = Review.class)
    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewDto reviewDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Review createdReview = reviewService.createReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update existing review", response = Review.class)
    public ResponseEntity<Review> updateReview(@PathVariable int id, @Valid @RequestBody ReviewDto reviewDto,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Review> updatedReview = reviewService.updateReview(id, reviewDto);
        return updatedReview.map(review -> ResponseEntity.ok().body(review))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete review by ID", response = HttpStatus.class)
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable int id) {
        if (reviewService.deleteReview(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


