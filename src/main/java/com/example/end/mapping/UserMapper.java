package com.example.end.mapping;

import com.example.end.dto.UserCategoryDto;
import com.example.end.dto.UserDetailsDto;
import com.example.end.dto.UserDto;
import com.example.end.models.User;
import com.example.end.models.Category;
import com.example.end.models.Procedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    @Autowired
    private CategoryMapper categoryMapper;
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .hashPassword(user.getHashPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


    public User toEntity(UserDto user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .hashPassword(user.getHashPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserDetailsDto userDetailsToDto(User user) {

        return UserDetailsDto.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .description(user.getDescription())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .profileImageUrl(user.getProfileImageUrl())
                .portfolioImageUrls(new ArrayList<>(user.getPortfolioImageUrls()))
                .categoryIds(user.getCategories().stream().map(Category::getId).collect(Collectors.toList()))
                .procedureIds(user.getProcedures().stream().map(Procedure::getId).collect(Collectors.toList()))
                .build();


    }
    public UserCategoryDto userToCategoryDto(User user) {
        return UserCategoryDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .categories(categoryMapper.toDto(user.getCategories()))
                .build();
    }

}

