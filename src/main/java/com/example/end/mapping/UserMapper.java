package com.example.end.mapping;

import com.example.end.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper toDto(User user);

    User toEntity(UserMapper userDto);
}
