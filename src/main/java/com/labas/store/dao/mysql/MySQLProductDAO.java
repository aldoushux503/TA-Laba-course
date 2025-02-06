package com.labas.store.dao.mysql;
import com.labas.store.dao.IProductDAO;
import com.labas.store.exception.DAOException;
import com.labas.store.model.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLProductDAO extends MySQLAbstractDAO<Product, Long> implements IProductDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLProductDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Product WHERE product_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Product";
    private static final String INSERT = "INSERT INTO Product (name, price, description) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Product SET name = ?, price = ?, description = ? WHERE product_id = ?";
    private static final String DELETE = "DELETE FROM Product WHERE product_id = ?";

    @Override
    public Optional<Product> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<Product> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(Product product) {
        return save(INSERT, this::setProductParameters, product);
    }

    @Override
    public boolean update(Product product) {
        return update(UPDATE, this::setProductParametersWithId, product);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private Product mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("product_id");
            String name = resultSet.getString("name");
            Double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            return new Product(id, name, price, description);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to Product object", e);
            throw new RuntimeException("Error mapping row to Product object", e);
        }
    }

    private void setProductParameters(PreparedStatement statement, Product product) {
        try {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
        } catch (SQLException e) {
            LOGGER.error("Error setting product parameters", e);
            throw new RuntimeException("Error setting product parameters", e);
        }
    }

    private void setProductParametersWithId(PreparedStatement statement, Product product) {
        try {
            setProductParameters(statement, product);
            statement.setLong(4, product.getProductId());
        } catch (SQLException e) {
            LOGGER.error("Error setting product parameters with ID", e);
            throw new RuntimeException("Error setting product parameters with ID", e);
        }
    }
}