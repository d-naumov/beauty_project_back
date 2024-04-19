package com.example.end.service;

import com.example.end.dto.UserMetadataDto;
import com.example.end.exceptions.UserNotFoundException;
import com.example.end.mapping.UserMetadataMapping;
import com.example.end.models.User;
import com.example.end.models.UserMetadata;
import com.example.end.repository.UserMetadataRepository;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.UserMetadataService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserMetadataServiceImpl implements UserMetadataService {

    @Autowired
    private UserMetadataRepository userMetadataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMetadataMapping userMetadataMapping;

    @Override
    @Transactional
    public UserMetadataDto createProfileImage(Long userId, UserMetadataDto userMetadataDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        UserMetadata userMetadata = UserMetadata.builder()
                .profileImageUrl(userMetadataDto.getProfileImageUrl())
                .user(user)
                .build();
        userMetadata.setUser(user);
        userMetadataRepository.save(userMetadata);
        return userMetadataMapping.toDto(userMetadata);
    }

    @Override
    @Transactional
    public UserMetadataDto createPortfolioImages(Long userId, UserMetadataDto userMetadataDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<String> urls = userMetadataDto.getPortfolioImageUrls();
        String portfolioImageUrls = String.join(",", urls);
        UserMetadata userMetadata = UserMetadata.builder()
                .portfolioImageUrls(portfolioImageUrls)
                .user(user)
                .build();

        UserMetadata updatedUserMetadata = userMetadataRepository.save(userMetadata);

        return userMetadataMapping.toDto(updatedUserMetadata);
    }
    }


