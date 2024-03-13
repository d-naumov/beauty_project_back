package com.example.end.service.interfaces;

import com.example.end.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface CategoryService {
    List<Category> getAllCategories();

    Optional<Category> getCategoryById(int id);

    Category createCategory(Category category);

    Category updateCategory(int id, Category updatedCategory);

    void deleteCategory(int id);

    Set<Category> getCategoriesByIds(Set<Integer> categoryIds);
}
