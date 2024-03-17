package com.example.end.mapping;

import com.example.end.dto.ReviewDto;
import com.example.end.models.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDto toDto(Review review);

    Review toEntity(ReviewDto reviewDto);
}
