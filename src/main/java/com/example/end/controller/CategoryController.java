package com.example.end.controller;

import com.example.end.dto.CategoryDto;
import com.example.end.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

//@RestController
//@RequestMapping("/categories")
//public class CategoryController {
//
//    private final CategoryService categoryService;
//    private final CategoryMapper categoryMapper;
//
//    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
//        this.categoryService = categoryService;
//        this.categoryMapper = categoryMapper;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CategoryDto>> getAllCategories() {
//        List<CategoryDto> categories = categoryService.getAllCategories().stream()
//                .map(categoryMapper::toDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(categories);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
//        Optional<CategoryDto> category = categoryService.getCategoryById(id)
//                .map(categoryMapper::toDto);
//        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
//        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
//        CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
//        return ResponseEntity.ok(updatedCategory);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
//        categoryService.deleteCategory(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/byIds")
//    public ResponseEntity<Set<CategoryDto>> getCategoriesById(@RequestParam Set<Long> categoryIds) {
//        Set<CategoryDto> categories = categoryService.getCategoriesById(categoryIds).stream()
//                .map(categoryMapper::toDto)
//                .collect(Collectors.toSet());
//        return ResponseEntity.ok(categories);
//    }
//}
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories;
        try {
            categories = categoryService.getAllCategories();
            if (categories.isEmpty()) {
                throw new NoSuchElementException("Kategorie nicht gefunden");
            }
            return ResponseEntity.ok(categories);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("ID der Kategorie kann nicht leer oder null sein");
        }
        Optional<CategoryDto> category = categoryService.getCategoryById(id);
        return ResponseEntity.of(category);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        if (categoryDto == null) {
            throw new IllegalArgumentException("Die Kategorie kann nicht leer sein");
        }

        if (categoryDto.getId() != null && categoryDto.getId() != 0) {
            throw new IllegalArgumentException("Die ID der Kategorie muss leer oder null sein, um eine neue Kategorie zu erstellen");
        }

        if (categoryDto.getName() == null || categoryDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Der Name der Kategorie darf nicht leer sein");
        }

        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        if (categoryDto == null || categoryDto.getId() == null || categoryDto.getId() == 0) {
            throw new IllegalArgumentException("Die Kategorie kann nicht leer sein oder eine Null-ID haben");
        }

        CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("Die Kategorie-ID kann nicht leer oder Null sein");
        }

        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}