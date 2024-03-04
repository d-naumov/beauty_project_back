package com.example.end.service;


import com.example.end.entity.ServiceCategory;
import com.example.end.repository.interfaces.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCategoryService {

    private ServiceCategoryRepository categoryRepository;

    @Autowired
    public ServiceCategoryService(ServiceCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ServiceCategory addServiceCategory(ServiceCategory category) {
        // Реализация добавления категории услуг
        return categoryRepository.save(category);
    }

    public ServiceCategory getServiceCategoryById(Long categoryId) {
        // Реализация получения категории услуг по идентификатору
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public ServiceCategory updateServiceCategory(Long categoryId, ServiceCategory updatedCategory) {
        // Реализация обновления информации о категории услуг
        Optional<ServiceCategory> existingCategory = categoryRepository.findById(categoryId);

        if (existingCategory.isPresent()) {
            ServiceCategory category = existingCategory.get();
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

