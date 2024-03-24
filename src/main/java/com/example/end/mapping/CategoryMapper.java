package com.example.end.mapping;

import com.example.end.dto.CategoryDto;
import com.example.end.models.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category toEntity(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}


