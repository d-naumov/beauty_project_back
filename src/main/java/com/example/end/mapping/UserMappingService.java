package com.example.end.mapping;

import com.example.end.models.dto.UserDto;
import com.example.end.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMappingService {

    UserDto mapEntityToDto(User entity);

    User mapDtoToEntity(UserDto dto);


}
