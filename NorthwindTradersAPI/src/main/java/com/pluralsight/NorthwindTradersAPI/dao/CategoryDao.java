package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAll();
    Category getById(int id);
    Category insert(Category category);
    void update(int id, Category category);
}
