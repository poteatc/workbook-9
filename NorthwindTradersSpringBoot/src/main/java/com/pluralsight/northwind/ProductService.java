package com.pluralsight.northwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private final SimpleProductDao simpleProductDao;

    // Autowired tells Spring to automatically inject a
    // object into this constructor when a RegistrationService
    // is created, more on this later
    // When there’s only one constructor with args, @autowired
    // is optional
    @Autowired
    public ProductService(SimpleProductDao simpleProductDao) {
        this.simpleProductDao = simpleProductDao;
    }

    public List<Product> getAllProducts() {
        return simpleProductDao.getAll();
    }

    public void addProduct(Product product) {
        simpleProductDao.add(product);
    }
}
