package com.example.end.mapping;

import com.example.end.dto.UserDto;
import com.example.end.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
