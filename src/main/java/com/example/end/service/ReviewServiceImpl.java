package com.example.end.service;

import com.example.end.dto.ReviewDto;
import com.example.end.exceptions.UserNotFoundException;
import com.example.end.mapping.ReviewMapper;
import com.example.end.models.Review;
import com.example.end.models.User;
import com.example.end.repository.ReviewRepository;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviewMapper.toDtoList(reviews);
    }

    public List<ReviewDto> getReviewsByMaster(Long masterId) {
        List<Review> reviews = reviewRepository.findByUserId(masterId);
        return reviewMapper.toDtoList(reviews);
    }

    @Override
    public ReviewDto addReview(Long userId, ReviewDto reviewDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        Review review = reviewMapper.toEntity(reviewDto);
        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toDto(savedReview);
    }

    @Override
    public double getMasterRating(Long masterId) {
        List<Review> reviews = reviewRepository.findByUserId(masterId);
        if (reviews.isEmpty()) {
            return 0;
        }
        double totalRating = reviews.stream().mapToInt(Review::getRating).sum();
        return totalRating / reviews.size();
    }
    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with id: " + reviewId));

        reviewRepository.delete(review);
    }
}