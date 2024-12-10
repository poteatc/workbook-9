package com.pluralsight.northwind;

import java.util.List;

public interface ProductDao {
    void add(Product product);
    List<Product> getAll();
    void deleteProductById(int id);
    void updateProduct(Product p, String name, String category, double price);
    Product searchProductById(int id);
}
