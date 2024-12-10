package com.pluralsight.northwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private final ProductDao productDao;

    // Autowired tells Spring to automatically inject a
    // object into this constructor when a RegistrationService
    // is created, more on this later
    // When there’s only one constructor with args, @autowired
    // is optional
    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public void addProduct(Product product) {
        productDao.add(product);
    }
}
