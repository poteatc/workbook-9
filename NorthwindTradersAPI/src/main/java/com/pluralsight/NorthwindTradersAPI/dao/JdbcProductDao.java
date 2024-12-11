package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Product;
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
        this.dataSource = dataSource;
    }

    /*
        private int productId;
    private String productName;
    private int categoryId;
    private double unitPrice;
     */
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = """
                SELECT ProductID, ProductName, CategoryID, UnitPrice
                FROM products
                """;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String categoryName = rs.getString("ProductName");
                int categoryID = rs.getInt("CategoryID");
                double unitPrice = rs.getDouble("UnitPrice");
                products.add(new Product(productID, categoryName, categoryID, unitPrice));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        String query = """
                SELECT ProductID, ProductName, CategoryID, UnitPrice
                FROM products
                WHERE ProductID = ?
                """;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String categoryName = rs.getString("ProductName");
                int categoryID = rs.getInt("CategoryID");
                double unitPrice = rs.getDouble("UnitPrice");
                return new Product(productID, categoryName, categoryID, unitPrice);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
