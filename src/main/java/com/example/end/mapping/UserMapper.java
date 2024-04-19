package com.example.end.mapping;

import com.example.end.dto.UserDetailsDto;
import com.example.end.dto.UserDto;
import com.example.end.models.User;
import com.example.end.models.Category;
import com.example.end.models.Procedure;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


    public User toEntity(UserDto user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    public User userDetailsToEntity(UserDetailsDto user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
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
                .categoryIds(user.getCategories().stream().map(Category::getId).collect(Collectors.toList()))
                .procedureIds(user.getProcedures().stream().map(Procedure::getId).collect(Collectors.toList()))
                .build();
    }
}

