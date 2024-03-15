package com.example.end.service.interfaces;

import com.example.end.dto.ReviewDto;
import com.example.end.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ReviewService {
    Review createReview(ReviewDto reviewDto);

    List<Review> getAllReviews();

    Optional<Review> getReviewById(Long id);

    Optional<Review> updateReview(Long id, ReviewDto reviewDto);

    boolean deleteReview(Long id);
    // Методы для управления отзывами
}
