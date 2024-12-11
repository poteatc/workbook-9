package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {
    List<Product> products = new ArrayList<>();

    public ProductsController() {
        products.add(new Product(1, "Apple", 1, 1.50));
        products.add(new Product(2, "Green Beans", 2, 2.00));
        products.add(new Product(3, "Turkey", 3, 4.99));
        products.add(new Product(4, "Water", 4, .89));
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return products;
    }

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable int id) {
        return products.stream().findFirst().orElse(null);
    }
}
