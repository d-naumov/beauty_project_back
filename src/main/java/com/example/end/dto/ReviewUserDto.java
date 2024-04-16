package com.example.end.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUserDto {

    @NotNull(message = "User cannot be null")
    @Schema(description = "User who left the review")
    private Long userId;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 1000, message = "Content length must be less than or equal to 1000 characters")
    @Schema(description = "Content of the review")
    private String content;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating should be at least 1")
    @Max(value = 5, message = "Rating should be at most 5")
    @Schema(description = "Rating of the review", example = "5")
    private int rating;

    @NotNull(message = "Date and time cannot be null")
    @NotBlank(message = "Date and time cannot be blank")
    @Schema(description = "Date and time of the review", example = "2024-03-16T10:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private String createdAt;
}
