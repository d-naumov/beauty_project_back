package com.example.end.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "User cannot be null")
    @Schema(description = "User who left the review")
    private UserDto user;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 1000, message = "Content length must be less than or equal to 1000 characters")
    @Schema(description = "Content of the review")
    private String content;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 1000, message = "Content length must be less than or equal to 1000 characters")
    @Schema(description = "Rating of the review", example = "5")
    private int rating;

    @NotNull(message = "Date and time cannot be null")
    @NotBlank(message = "Date and time cannot be blank")
    @Schema(description = "Date and time of the review", example = "2024-03-16T10:00:00")
    private LocalDateTime createdAt;


}
