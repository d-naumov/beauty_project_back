package com.example.end.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ReviewDto", description = "Master's reviews")
public class ReviewDto {


    @Schema(description = "Unique identifier of the review", example = "1")
    private Long id;

    @Schema(description = "User who left the review")
    private UserDto user;

    @Schema(description = "Content of the review")
    private String content;

    @Schema(description = "Rating of the review", example = "5")
    private int rating;

    @Schema(description = "Date and time of the review", example = "2024-03-16T10:00:00")
    private LocalDateTime createdAt;


}
