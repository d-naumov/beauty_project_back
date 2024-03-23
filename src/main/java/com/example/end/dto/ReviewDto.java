package com.example.end.dto;

import com.example.end.models.Review;
import com.example.end.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ReviewDto", description = "Master's reviews")
public class ReviewDto {


    @Schema(description = "Content of the review")
    private String content;

    @Schema(description = "Rating of the review", example = "5")
    private int rating;




}
