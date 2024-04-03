package com.example.end.service.interfaces;

import com.example.end.dto.ReviewDto;
import com.example.end.models.Review;
import com.example.end.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ReviewService {


    ReviewDto addReview(ReviewDto reviewDto);

    double getMasterRating(Long masterId);

    void deleteReview(Long reviewId);

    List<ReviewDto> getAllReviews();

    List<ReviewDto> getReviewsByMaster(Long masterId);
}
