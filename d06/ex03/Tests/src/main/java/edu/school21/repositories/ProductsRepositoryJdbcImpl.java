package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{
    private final Connection connection;

    public ProductsRepositoryJdbcImpl(Connection connection){
       this.connection = connection;
    }

    @Override
    public List<Product> findAll() throws SQLException{
        List<Product> productList;

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

            productList = new ArrayList<>();
            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                ));
            }
        }
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE products.id = " + id)) {

            resultSet.next();

            Product product = new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price")
            );
            return Optional.of(product);
        }
    }

    @Override
    public void update(Product product) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET name = ?, price = ? WHERE id = ?")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void save(Product product) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products(name, price) VALUES(?, ?)")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException{
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
