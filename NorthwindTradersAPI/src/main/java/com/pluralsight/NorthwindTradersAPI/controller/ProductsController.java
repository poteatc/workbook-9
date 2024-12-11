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
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class ProductsController {
    List<Product> products = List.of(
            new Product(1, "Apple", 1, 1.50),
            new Product(2, "Green Beans", 2, 2.00),
            new Product(3, "Turkey", 3, 4.99),
            new Product(4, "Water", 4, .89)
    );

    @GetMapping("/products")
    public List<Product> findAll() {
        return products;
    }

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable int id) {
        return products.stream().findFirst().orElse(null);
    }

    @GetMapping("/products/filter")
    public ResponseEntity<?> filterByAttributes(@RequestParam(required = false) String name
                                            , @RequestParam(required = false) Integer categoryId
                                            , @RequestParam(required = false) Double price) {
        List<Product> filteredProducts = products.stream()
                .filter(product -> name == null || product.getProductName().equals(name))
                .filter(product -> categoryId == null || product.getProductId() == categoryId)
                .filter(product -> price == null || product.getUnitPrice() == price).toList();

        if (filteredProducts.isEmpty()) {
            // Return 200 OK to indicate request was completed with a custom error message
            return ResponseEntity.status(HttpStatus.OK).body("No products found matching the criteria");
        }

        // Return the filtered products with 200 OK
        return ResponseEntity.ok(filteredProducts);
    }
}
