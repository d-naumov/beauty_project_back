package com.example.end.mapping;

import com.example.end.dto.RoleDto;
import com.example.end.dto.UserDto;
import com.example.end.models.Role;
import com.example.end.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
}
