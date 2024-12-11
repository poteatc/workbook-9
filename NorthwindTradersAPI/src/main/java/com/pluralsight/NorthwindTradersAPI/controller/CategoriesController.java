package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {

    List<Category> categories = new ArrayList<>();

    public CategoriesController() {
        categories.add(new Category(1, "Fruit"));
        categories.add(new Category(2, "Vegetable"));
        categories.add(new Category(3, "Meat"));
        categories.add(new Category(4, "Drink"));
    }

    @GetMapping("/categories")
    public List<Category> findAll() {
        return categories;
    }

    @GetMapping("/categories/{id}")
    public Category findCategoryById(@PathVariable int id) {
        return categories.stream().findFirst().orElse(null);
    }

    @GetMapping("/categories/filter")
    public ResponseEntity<?> filterByAttributes(@RequestParam(required = false) Integer id
            , @RequestParam(required = false) String name) {
        List<Category> filteredCategories = categories.stream()
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
}
