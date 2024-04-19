package com.example.end.controller;

import com.example.end.controller.api.UserMetadataApi;
import com.example.end.dto.UserMetadataDto;
import com.example.end.service.interfaces.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserMetadataController implements UserMetadataApi {
    public final UserMetadataService userMetadataService;

   @Override
   public UserMetadataDto createProfileImage(Long userId, UserMetadataDto userMetadataDto){
       return userMetadataService.createProfileImage(userId, userMetadataDto);
   }

   @Override
    public UserMetadataDto createPortfolioImages(Long userId, UserMetadataDto userMetadataDto){
       return userMetadataService.createPortfolioImages(userId,userMetadataDto);
    }
}
