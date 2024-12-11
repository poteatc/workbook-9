package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
