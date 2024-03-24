package com.example.end.service;

import com.example.end.dto.CategoryDto;
import com.example.end.exceptions.CategoryNotFoundException;
import com.example.end.mapping.CategoryMapper;
import com.example.end.models.Category;
import com.example.end.repository.CategoryRepository;
import com.example.end.service.interfaces.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("Die Liste der Kategorien ist leer");
        }
        return categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> getCategoryById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            return Optional.of(categoryMapper.toDto(categoryOptional.get()));
        } else {
            throw new CategoryNotFoundException("Die Kategorie wurde nicht gefunden: " + id);
        }
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            throw new IllegalArgumentException("Die Kategorie darf nicht leer sein");
        }

        if (categoryDto.getId() != null && categoryDto.getId() != 0) {
            throw new IllegalArgumentException("Die Kategorie-ID darf nicht leer oder null sein");
        }

        if (categoryDto.getName() == null || categoryDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Der Name der Kategorie darf nicht leer sein");
        }

        Category category = categoryMapper.toEntity(categoryDto);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto updatedCategoryDto) {
        if (updatedCategoryDto == null) {
            throw new IllegalArgumentException("Die zu aktualisierende Kategorie darf nicht leer sein");
        }

        if (updatedCategoryDto.getId() == null || updatedCategoryDto.getId() == 0) {
            throw new IllegalArgumentException("Die Kategorie-ID darf nicht leer oder null sein");
        }

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Die Kategorie wurde nicht gefunden mit ID: " + id));

        Category updatedCategory = categoryMapper.toEntity(updatedCategoryDto);
        updatedCategory.setId(existingCategory.getId());

        Category savedCategory = categoryRepository.save(updatedCategory);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundException("Die Kategorie mit der ID " + id + " wurde nicht gefunden");
        }

    }
}