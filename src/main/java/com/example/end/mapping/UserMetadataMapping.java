package com.example.end.mapping;

import com.example.end.dto.UserMetadataDto;
import com.example.end.models.UserMetadata;
import org.springframework.stereotype.Service;

@Service
public class UserMetadataMapping {
    public UserMetadataDto toDto(UserMetadata userMetadata) {
        return UserMetadataDto.builder()
                .id(userMetadata.getId())
                .profileImageUrl(userMetadata.getProfileImageUrl())
                .portfolioImageUrls(userMetadata.getPortfolioImageUrls())
                .userId(userMetadata.getUser().getId())
                .build();
    }
}
