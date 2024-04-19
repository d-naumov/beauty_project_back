package com.example.end.dto;

import com.example.end.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMetadataDto {

    private Long id;

    @NotBlank(message = "Profile image URL cannot be blank")
    @Size(max = 255, message = "Profile image URL cannot be longer than 255 characters")
    private String profileImageUrl;

    @NotNull
    @Size(min = 1)
    private List<String> portfolioImageUrls;


    @NotNull(message = "User ID cannot be null")
    @Schema(description = "User ID associated with the booking", example = "1")
    private Long userId;
}
