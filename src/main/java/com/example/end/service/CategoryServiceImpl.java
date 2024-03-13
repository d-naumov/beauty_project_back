package com.example.end.service;


import com.example.end.models.Category;
import com.example.end.repository.CategoryRepository;
import com.example.end.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        // Реализация получения всех категорий
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        // Реализация получения категории по идентификатору
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        // Реализация создания категории
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int id, Category updatedCategory) {
        // Реализация обновления категории
        Optional<Category> existingCategory = categoryRepository.findById(id);

        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            // Обновление атрибутов категории
            category.setName(updatedCategory.getName());
            return categoryRepository.save(category);
        } else {
            return null; // Или бросить исключение, в зависимости от логики приложения
        }
    }

    @Override
    public void deleteCategory(int id) {
        // Реализация удаления категории
        categoryRepository.deleteById(id);
    }

    @Override
    public Set<Category> getCategoriesByIds(Set<Integer> categoryIds) {
        // Реализация получения категорий по списку идентификаторов
        return new HashSet<>(categoryRepository.findAllById(categoryIds));
    }
}


