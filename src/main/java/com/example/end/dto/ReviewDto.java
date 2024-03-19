package com.example.end.dto;

import com.example.end.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ReviewDto", description = "Master's reviews")
public class ReviewDto {

    @Schema(description = "Unique identifier of the review", example = "1")
    private Long id;

    @Schema(description = "Client who left the review")
    private UserDto client;

    @Schema(description = "Content of the review")
    private String content;

    @Schema(description = "Rating of the review", example = "5")
    private int rating;
}
