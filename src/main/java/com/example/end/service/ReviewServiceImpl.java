package com.example.end.service;

import com.example.end.dto.ReviewDto;
import com.example.end.exceptions.ReviewNotFoundException;
import com.example.end.mapping.ReviewMapper;
import com.example.end.models.Review;
import com.example.end.repository.ReviewRepository;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<ReviewDto> getReviewsByMaster(Long masterId) {
        List<Review> reviews = reviewRepository.findByUserId(masterId);
        return reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {
       Review review = reviewMapper.toEntity(reviewDto);
       Review createdReview = reviewRepository.save(review);
       return reviewMapper.toDto(createdReview);
    }
    @Override
    public double getMasterRating(Long masterId) {
        List<Review> reviews = reviewRepository.findByUserId(masterId);

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);
    }
    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + reviewId));

        reviewRepository.delete(review);
    }
}