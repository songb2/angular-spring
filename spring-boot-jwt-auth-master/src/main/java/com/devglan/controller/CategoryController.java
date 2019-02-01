package com.devglan.controller;

import com.devglan.exception.*;
import com.devglan.model.ApiResponse;
import com.devglan.model.Category;
import com.devglan.dao.CategoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryDao categoryRepository;

    @GetMapping("/categories")
    public ApiResponse<List<Category>> getAllCategories() {
        List<Category> list = new ArrayList<>();
        categoryRepository.findAll().iterator().forEachRemaining(list::add);
        
		return new ApiResponse<>(HttpStatus.OK.value(), "Category list fetched successfully.",list);
    }

    @PostMapping("/categories")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/categories/{id}")
    public ApiResponse<Category> getCategoryById(@PathVariable(value = "id") int categoryId) {
        Category category = categoryRepository.findById(categoryId).
            orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return new ApiResponse<>(HttpStatus.OK.value(), "Get category successfully", category);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable(value = "id") int categoryId, @Valid @RequestBody Category categoryDetails) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setName(categoryDetails.getName());

        Category updatedCategory = categoryRepository.save(category);
        return updatedCategory;
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.delete(category);

        return ResponseEntity.ok().build();
    }
}