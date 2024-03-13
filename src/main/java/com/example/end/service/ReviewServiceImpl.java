package com.example.end.service;


import com.example.end.dto.ReviewDto;
import com.example.end.models.Review;
import com.example.end.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Override
    public Review createReview(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        return null;
    }

    @Override
    public Optional<Review> getReviewById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Review> updateReview(int id, ReviewDto reviewDto) {
        return Optional.empty();
    }

    @Override
    public boolean deleteReview(int id) {
        return false;
    }
}

