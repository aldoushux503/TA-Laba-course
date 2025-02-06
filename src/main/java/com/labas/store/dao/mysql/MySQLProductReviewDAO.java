package com.labas.store.dao.mysql;

import com.labas.store.dao.*;
import com.labas.store.exception.DAOException;

import com.labas.store.model.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ProductReviewDAO.
 */
public class MySQLProductReviewDAO extends MySQLAbstractDAO<ProductReview, Long> implements IProductReviewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLProductReviewDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Product_review WHERE review_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Product_review";
    private static final String INSERT = "INSERT INTO Product_review (title, rating, created_at, product_id, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Product_review SET title = ?, rating = ?, created_at = ?, product_id = ?, user_id = ? WHERE review_id = ?";
    private static final String DELETE = "DELETE FROM Product_review WHERE review_id = ?";

    private final IProductDAO productDAO;
    private final IUserDAO userDAO;

    public MySQLProductReviewDAO(IProductDAO productDAO, IUserDAO userDAO) {
        this.productDAO = productDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Optional<ProductReview> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<ProductReview> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(ProductReview review) {
        return save(INSERT, this::setProductReviewParameters, review);
    }

    @Override
    public boolean update(ProductReview review) {
        return update(UPDATE, this::setProductReviewParametersWithId, review);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private ProductReview mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("review_id");
            String title = resultSet.getString("title");
            Double rating = resultSet.getDouble("rating");
            Timestamp createdAt = resultSet.getTimestamp("created_at");

            Long productId = resultSet.getLong("product_id");
            Optional<Product> productOptional = productDAO.findById(productId);

            Long userId = resultSet.getLong("user_id");
            Optional<User> userOptional = userDAO.findById(userId);

            if (productOptional.isEmpty() || userOptional.isEmpty()) {
                LOGGER.warn("Related entities not found for ProductReview: product ID={}, user ID={}", productId, userId);
                return null;
            }

            Product product = productOptional.get();
            User user = userOptional.get();

            return new ProductReview(id, title, rating, createdAt.toString(), product, user);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to ProductReview object", e);
            throw new RuntimeException("Error mapping row to ProductReview object", e);
        }
    }

    private void setProductReviewParameters(PreparedStatement statement, ProductReview review) {
        try {
            statement.setString(1, review.getTitle());
            statement.setDouble(2, review.getRating());
            statement.setTimestamp(3, Timestamp.valueOf(review.getCreatedAt()));
            statement.setLong(4, review.getProduct().getProductId());
            statement.setLong(5, review.getUser().getUserId());
        } catch (SQLException e) {
            LOGGER.error("Error setting product review parameters", e);
            throw new RuntimeException("Error setting product review parameters", e);
        }
    }

    private void setProductReviewParametersWithId(PreparedStatement statement, ProductReview review) {
        try {
            setProductReviewParameters(statement, review);
            statement.setLong(6, review.getReviewId());
        } catch (SQLException e) {
            LOGGER.error("Error setting product review parameters with ID", e);
            throw new RuntimeException("Error setting product review parameters with ID", e);
        }
    }
}
