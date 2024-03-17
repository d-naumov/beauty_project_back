package com.example.end.service;

import com.example.end.dto.BookingDto;
import com.example.end.dto.ReviewDto;
import com.example.end.mapping.ReviewMapper;
import com.example.end.models.Booking;
import com.example.end.models.Review;
import com.example.end.models.User;
import com.example.end.repository.ReviewRepository;
import com.example.end.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;
    private UserServiceImpl userService;

    @Override
    public Review createReview(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        return null;
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Review> updateReview(Long id, ReviewDto reviewDto) {
        return Optional.empty();
    }

    @Override
    public boolean deleteReview(Long id) {
        return false;
    }

    @Override
    public List<ReviewDto> getReviewsForMaster(User master) {
        List<Review> reviews = reviewRepository.findByMaster(master);
        return reviews.stream()
                .map(reviewMapper::toDto)
                .toList();
    }
}

