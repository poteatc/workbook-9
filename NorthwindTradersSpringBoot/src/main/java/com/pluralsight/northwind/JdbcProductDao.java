package com.pluralsight.northwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {
    private DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource) {
        // the dataSource is injected from the Configuration class
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = """
                    SELECT ProductID, ProductName, categories.CategoryName, UnitPrice 
                    FROM products
                    JOIN categories ON categories.CategoryID = products.CategoryID;
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                String category = rs.getString("CategoryName");
                double price = rs.getDouble("UnitPrice");
                products.add(new Product(id, name, category, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void deleteProductById(int id) {

    }

    @Override
    public void updateProduct(Product p, String name, String category, double price) {

    }

    @Override
    public Product searchProductById(int id) {
        return null;
    }
}
