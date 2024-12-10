package com.pluralsight.northwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Component tells Spring to create an object from the class and
// identify it as RegistrationDAO type. If any other component
// requires a RegistrationDAO type it will use this object created
// by Spring.

// The default scope for the object is singleton. Which means that
// it will be available for the duration of the application

@Component
public class SimpleProductDao implements ProductDao {

    private final List<Product> products;

    @Autowired
    public SimpleProductDao(List<Product> products) {
        this.products = new ArrayList<>();
        this.products.add(new Product(1, "Apple", "Fruit", 0.99));
        this.products.add(new Product(2, "Banana", "Fruit", 0.59));
        this.products.add(new Product(3, "Carrot", "Vegetable", 1.29));
        this.products.add(new Product(4, "Milk", "Dairy", 2.49));
        this.products.add(new Product(5, "Eggs", "Dairy", 3.19));
        this.products.add(new Product(6, "Chicken Breast", "Meat", 5.99));
        this.products.add(new Product(7, "Bread", "Bakery", 1.89));
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}
