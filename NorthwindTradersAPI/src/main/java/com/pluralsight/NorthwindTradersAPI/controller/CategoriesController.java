package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {
    private CategoryDao categoryDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping("/categories")
    public List<Category> findAll() {
        return categoryDao.getAll();
    }

    @GetMapping("/categories/{id}")
    public Category findCategoryById(@PathVariable int id) {
        return categoryDao.getById(id);
        //return categories.stream().findFirst().orElse(null);
    }

    @GetMapping("/categories/filter")
    public ResponseEntity<?> filterByAttributes(@RequestParam(required = false) Integer id
            , @RequestParam(required = false) String name) {
        List<Category> filteredCategories = findAll().stream()
                .filter(category -> id == null || category.getCategoryId() == id)
                .filter(category -> name == null || category.getCategoryName().equalsIgnoreCase(name))
                .toList();

        if (filteredCategories.isEmpty()) {
            // Return 200 OK to indicate request was completed with a custom error message
            return ResponseEntity.status(HttpStatus.OK).body("No categories found matching the criteria");
        }

        // Return the filtered categories with 200 OK
        return ResponseEntity.ok(filteredCategories);
    }

    @PostMapping("/categories")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category addCategory(
            @RequestBody Category category
    ) {
        Category newCategory = categoryDao.insert(category);
        return newCategory;
    }
}
