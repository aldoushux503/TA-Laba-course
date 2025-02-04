package com.labas.store.dao.impl;
import com.labas.store.dao.AbstractDAO;
import com.labas.store.dao.ProductDAO;
import com.labas.store.exception.DAOException;
import com.labas.store.model.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl extends AbstractDAO<Product, Long> implements ProductDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Product WHERE product_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Product";
    private static final String INSERT = "INSERT INTO Product (name, price, description) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Product SET name = ?, price = ?, description = ? WHERE product_id = ?";
    private static final String DELETE = "DELETE FROM Product WHERE product_id = ?";

    @Override
    public Optional<Product> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet)); // Return product if found
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding product by ID: {}", id, e);
            throw new DAOException("Error finding product by ID: " + id, e);
        }

        return Optional.empty(); // Explicitly return empty Optional if not found
    }

    @Override
    public List<Product> findAll() throws DAOException {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                products.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all products", e);
            throw new DAOException("Error finding all products", e);
        }

        return products;
    }

    @Override
    public boolean save(Product product) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());

            return statement.executeUpdate() > 0; // Return true if a row was inserted
        } catch (SQLException e) {
            logger.error("Error saving product: {}", product, e);
            throw new DAOException("Error saving product: " + product, e);
        }
    }

    @Override
    public boolean update(Product product) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setLong(4, product.getProductId());

            return statement.executeUpdate() > 0; // Return true if a row was updated
        } catch (SQLException e) {
            logger.error("Error updating product: {}", product, e);
            throw new DAOException("Error updating product: " + product, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0; // Return true if a row was deleted
        } catch (SQLException e) {
            logger.error("Error deleting product by ID: {}", id, e);
            throw new DAOException("Error deleting product by ID: " + id, e);
        }
    }

    private Product mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        String description = resultSet.getString("description");

        return new Product(id, name, price, description);
    }
}
