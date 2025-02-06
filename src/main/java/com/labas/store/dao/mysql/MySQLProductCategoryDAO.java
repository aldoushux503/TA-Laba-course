package com.labas.store.dao.mysql;

import com.labas.store.dao.*;
import com.labas.store.exception.DAOException;

import com.labas.store.model.entity.*;
import com.labas.store.util.CompositeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ProductCategoryDAO.
 */
public class MySQLProductCategoryDAO extends MySQLAbstractDAO<ProductCategory, CompositeKey<Long, Long>> implements IProductCategoryDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLProductCategoryDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Product_category WHERE category_id = ? AND product_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Product_category";
    private static final String INSERT = "INSERT INTO Product_category (category_id, product_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE Product_category SET category_id = ?, product_id = ? WHERE category_id = ? AND product_id = ?";
    private static final String DELETE = "DELETE FROM Product_category WHERE category_id = ? AND product_id = ?";

    private final ICategoryDAO categoryDAO;
    private final IProductDAO productDAO;

    public MySQLProductCategoryDAO(ICategoryDAO categoryDAO, IProductDAO productDAO) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

    @Override
    public Optional<ProductCategory> findById(CompositeKey<Long, Long> id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<ProductCategory> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(ProductCategory productCategory) {
        return save(INSERT, this::setProductCategoryParameters, productCategory);
    }

    @Override
    public boolean update(ProductCategory newProductCategory) {
        return update(UPDATE, this::setProductCategoryParametersWithId, newProductCategory);
    }

    @Override
    public boolean delete(CompositeKey<Long, Long> id) {
        return delete(DELETE, id);
    }

    private ProductCategory mapRow(ResultSet resultSet) {
        try {
            Long categoryId = resultSet.getLong("category_id");
            Long productId = resultSet.getLong("product_id");

            // Получаем связанные сущности
            Optional<Category> categoryOptional = categoryDAO.findById(categoryId);
            Optional<Product> productOptional = productDAO.findById(productId);

            if (categoryOptional.isEmpty() || productOptional.isEmpty()) {
                LOGGER.warn("Related entities not found for ProductCategory: category ID={}, product ID={}", categoryId, productId);
                return null; // Можно выбросить исключение или вернуть null в зависимости от требований
            }

            Category category = categoryOptional.get();
            Product product = productOptional.get();

            return new ProductCategory(category, product);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to ProductCategory object", e);
            throw new RuntimeException("Error mapping row to ProductCategory object", e);
        }
    }

    private void setProductCategoryParameters(PreparedStatement statement, ProductCategory productCategory) {
        try {
            statement.setLong(1, productCategory.getCategory().getCategoryId());
            statement.setLong(2, productCategory.getProduct().getProductId());
        } catch (SQLException e) {
            LOGGER.error("Error setting product category parameters", e);
            throw new RuntimeException("Error setting product category parameters", e);
        }
    }

    private void setProductCategoryParametersWithId(PreparedStatement statement, ProductCategory productCategory) {
        try {
            statement.setLong(1, productCategory.getCategory().getCategoryId());
            statement.setLong(2, productCategory.getProduct().getProductId());
            statement.setLong(3, productCategory.getCategory().getCategoryId());
            statement.setLong(4, productCategory.getProduct().getProductId());
        } catch (SQLException e) {
            LOGGER.error("Error setting product category parameters with ID", e);
            throw new RuntimeException("Error setting product category parameters with ID", e);
        }
    }
}