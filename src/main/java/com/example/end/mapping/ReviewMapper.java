package com.example.end.mapping;

import com.example.end.dto.ReviewDto;

import com.example.end.models.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewMapper {
    @Autowired
    private UserMapper userMapper;

    public ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .user(userMapper.toDto(review.getUser()))  // Map User to UserDto
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public Review toEntity(ReviewDto reviewDto) {
        // Assuming the UserDto is set correctly in the ReviewDto
        return Review.builder()
                .id(reviewDto.getId())
                .content(reviewDto.getContent())
                .rating(reviewDto.getRating())
                .createdAt(reviewDto.getCreatedAt())
                .user(userMapper.toEntity(reviewDto.getUser()))  // Map UserDto to User
                .build();
    }

    public List<ReviewDto> toDtoList(List<Review> reviews) {
        return reviews.stream().map(this::toDto).collect(Collectors.toList());
    }
}
//    public List<ReviewDto> toDtoList(List<Review> reviews) {
//        return reviews.stream().map(this::toDto).collect(Collectors.toList());
//    }
//}

//        return ReviewDto.builder()
//                .id(review.getId())
//                .content(review.getContent())
//                .rating(review.getRating())
//                .createdAt(review.getCreatedAt())
//                .user(review.getUser())
//                .build();
//    }
//
//    public Review toEntity(ReviewDto reviewDto) {
//        return Review.builder()
//                .id(reviewDto.getId())
//                .content(reviewDto.getContent())
//                .rating(reviewDto.getRating())
//                .createdAt(reviewDto.getCreatedAt())
//                .user(reviewDto.getUser())
//                .build();
//    }
//
//    public List<ReviewDto> toDtoList(List<Review> reviews) {
//        return reviews.stream().map(this::toDto).collect(Collectors.toList());
//    }
//}



