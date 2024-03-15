package com.example.end.service.interfaces;

import com.example.end.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public interface CategoryService {
    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    Category createCategory(Category category);

    Category updateCategory(Long id, Category updatedCategory);

    void deleteCategory(Long id);

    Set<Category> getCategoriesById(Set<Long> categoryId);
}
