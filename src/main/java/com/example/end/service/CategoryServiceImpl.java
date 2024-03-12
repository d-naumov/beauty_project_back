package com.example.end.service;


import com.example.end.models.Category;
import com.example.end.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addServiceCategory(Category category) {
        // Реализация добавления категории услуг
        return categoryRepository.save(category);
    }

    public Category getServiceCategoryById(Long categoryId) {
        // Реализация получения категории услуг по идентификатору
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category updateServiceCategory(Long categoryId, Category updatedCategory) {
        // Реализация обновления информации о категории услуг
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);

        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            // Обновление атрибутов категории
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());
            return categoryRepository.save(category);
        } else {
            return null; // Или бросить исключение, в зависимости от логики приложения
        }
    }

    public void deleteServiceCategory(Long categoryId) {
        // Реализация удаления категории услуг
        categoryRepository.deleteById(categoryId);
    }
}

