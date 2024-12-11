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

    @Override
    public Product insert(Product product) {
        /*
            private int productId;
    private String productName;
    private int categoryId;
    private double unitPrice;
         */
        String insert = """
                INSERT INTO products (ProductName, UnitPrice)
                VALUES (?, ?)
                """;
        String select = """
                SELECT ProductID, ProductName, categories.CategoryID, categories.CategoryName, UnitPrice
                FROM products
                LEFT JOIN categories ON products.CategoryID = categories.CategoryID
                WHERE ProductName = ?;
                """;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getUnitPrice());
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " row(s) updated");

            preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, product.getProductName());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int categoryID = rs.getInt("CategoryID");
                double unitPrice = rs.getDouble("UnitPrice");
                return new Product(productID, productName, categoryID, unitPrice);
            }
            //            while (rs.next()) {
//                String productName = rs.getString("ProductName");
//                int categoryID = rs.getInt("CategoryID");
//                double unitPrice = rs.getDouble("UnitPrice");
//                return new Product(productName, categoryID, unitPrice);
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
