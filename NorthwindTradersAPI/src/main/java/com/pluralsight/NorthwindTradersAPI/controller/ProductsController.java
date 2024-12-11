package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class ProductsController {
    private ProductDao productDao;

    @Autowired
    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return productDao.getAll();
    }

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable int id) {
        return productDao.getById(id);
        //return products.stream().findFirst().orElse(null);
    }

    @GetMapping("/products/filter")
    public ResponseEntity<?> filterByAttributes(@RequestParam(required = false) String name
                                            , @RequestParam(required = false) Integer categoryId
                                            , @RequestParam(required = false) Double price) {
        List<Product> filteredProducts = findAll().stream()
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

    @PostMapping("/products")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product addProduct(
            @RequestBody Product product
    ) {
        Product newProduct = productDao.insert(product);

        return newProduct;
    }
}
