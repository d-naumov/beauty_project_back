package com.example.end.mapping;

import com.example.end.dto.CategoryDto;
import com.example.end.models.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    @InheritInverseConfiguration
    Category toEntity(CategoryDto categoryDto);
}
