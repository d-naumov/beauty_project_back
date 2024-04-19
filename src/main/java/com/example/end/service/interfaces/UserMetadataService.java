package com.example.end.service.interfaces;

import com.example.end.dto.UserMetadataDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserMetadataService {

    @Transactional
    UserMetadataDto createProfileImage(Long userId, UserMetadataDto userMetadataDto);


    @Transactional
    UserMetadataDto createPortfolioImages(Long userId, UserMetadataDto userMetadataDto);
}
