package com.labas.store.dao.impl;

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
public class ProductCategoryDAOImpl extends AbstractDAO<ProductCategory, CompositeKey<Long, Long>> implements IProductCategoryDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Product_category WHERE category_id = ? AND product_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Product_category";
    private static final String INSERT = "INSERT INTO Product_category (category_id, product_id) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM Product_category WHERE category_id = ? AND product_id = ?";
    private static final String UPDATE = "UPDATE Product_category SET category_id = ? AND product_id = ? WHERE category_id = ? AND product_id = ?";

    private final ICategoryDAO ICategoryDAO;
    private final IProductDAO IProductDAO;

    public ProductCategoryDAOImpl(ICategoryDAO ICategoryDAO, IProductDAO IProductDAO) {
        super();
        this.ICategoryDAO = ICategoryDAO;
        this.IProductDAO = IProductDAO;
    }

    @Override
    public Optional<ProductCategory> findById(CompositeKey<Long, Long> productCategoryId) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, productCategoryId.getK1());
            statement.setLong(2, productCategoryId.getK2());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error(
                    "Error finding product category by category ID: {} and product ID: {}",
                    productCategoryId.getK1(),
                    productCategoryId.getK2(),
                    e
            );
            throw new DAOException("Error finding product category by IDs", e);
        }

        return Optional.empty();
    }


    @Override
    public boolean save(ProductCategory productCategory) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setLong(1, productCategory.getCategory().getCategoryId());
            statement.setLong(2, productCategory.getProduct().getProductId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving product category: {}", productCategory, e);
            throw new DAOException("Error saving product category", e);
        }
    }

    @Override
    public boolean update(ProductCategory newProductCategory) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {


            statement.setLong(1, newProductCategory.getCategory().getCategoryId());
            statement.setLong(2, newProductCategory.getProduct().getProductId());

            statement.setLong(3, newProductCategory.getCategory().getCategoryId());
            statement.setLong(4, newProductCategory.getProduct().getProductId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating product category to {}", newProductCategory, e);
            throw new DAOException("Error updating product category", e);
        }
    }

    @Override
    public boolean delete(CompositeKey<Long, Long> productCategoryId) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, productCategoryId.getK1());
            statement.setLong(2, productCategoryId.getK2());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(
                    "Error deleting product category by category ID: {} and product ID: {}",
                    productCategoryId.getK1(),
                    productCategoryId.getK2(),
                    e
            );
            throw new DAOException("Error deleting product category", e);
        }
    }

    @Override
    public List<ProductCategory> findAll() throws DAOException {
        List<ProductCategory> productCategories = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                productCategories.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all product categories", e);
            throw new DAOException("Error finding all product categories", e);
        }

        return productCategories;
    }

    private ProductCategory mapRow(ResultSet resultSet) throws SQLException, DAOException {
        Long categoryId = resultSet.getLong("category_id");
        Category category = ICategoryDAO.findById(categoryId).orElse(null);

        Long productId = resultSet.getLong("product_id");
        Product product = IProductDAO.findById(productId).orElse(null);

        if (category == null || product == null) {
            throw new SQLException("Failed to map ProductCategory: missing related entities");
        }

        return new ProductCategory(category, product);
    }
}
