package com.example.end.mapping;

import com.example.end.dto.UserMetadataDto;
import com.example.end.models.UserMetadata;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserMetadataMapping {
    public UserMetadataDto toDto(UserMetadata userMetadata) {
        UserMetadataDto userMetadataDto = UserMetadataDto.builder()
                .id(userMetadata.getId())
                .profileImageUrl(userMetadata.getProfileImageUrl())
                .userId(userMetadata.getUser().getId())
                .build();

        if (userMetadata.getPortfolioImageUrls() != null) {
            List<String> portfolioImageUrls = Arrays.asList(userMetadata.getPortfolioImageUrls().split(","));
            userMetadataDto.setPortfolioImageUrls(portfolioImageUrls);
        }

        return userMetadataDto;
    }
}
