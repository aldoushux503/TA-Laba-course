package com.labas.store.dao.impl;

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
public class ProductReviewDAOImpl extends AbstractDAO<ProductReview, Long> implements IProductReviewDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProductReviewDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Product_review WHERE review_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Product_review";
    private static final String INSERT = "INSERT INTO Product_review (title, rating, created_at, product_id, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Product_review SET title = ?, rating = ?, created_at = ?, product_id = ?, user_id = ? WHERE review_id = ?";
    private static final String DELETE = "DELETE FROM Product_review WHERE review_id = ?";


    private final IProductDAO IProductDAO;
    private final IUserDAO IUserDAO;

    public ProductReviewDAOImpl(IProductDAO IProductDAO, IUserDAO IUserDAO) {
        super();
        this.IProductDAO = IProductDAO;
        this.IUserDAO = IUserDAO;
    }


    @Override
    public Optional<ProductReview> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding product review by ID: {}", id, e);
            throw new DAOException("Error finding product review by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<ProductReview> findAll() throws DAOException {
        List<ProductReview> reviews = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                reviews.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all product reviews", e);
            throw new DAOException("Error finding all product reviews", e);
        }

        return reviews;
    }

    @Override
    public boolean save(ProductReview review) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, review.getTitle());
            statement.setDouble(2, review.getRating());
            statement.setTimestamp(3, Timestamp.valueOf(review.getCreatedAt()));
            statement.setLong(4, review.getProduct().getProductId());
            statement.setLong(5, review.getUser().getUserId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving product review: {}", review, e);
            throw new DAOException("Error saving product review: " + review, e);
        }
    }

    @Override
    public boolean update(ProductReview review) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, review.getTitle());
            statement.setDouble(2, review.getRating());
            statement.setTimestamp(3, Timestamp.valueOf(review.getCreatedAt()));
            statement.setLong(4, review.getProduct().getProductId());
            statement.setLong(5, review.getUser().getUserId());
            statement.setLong(6, review.getReviewId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating product review: {}", review, e);
            throw new DAOException("Error updating product review: " + review, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting product review by ID: {}", id, e);
            throw new DAOException("Error deleting product review by ID: " + id, e);
        }
    }

    private ProductReview mapRow(ResultSet resultSet) throws SQLException, DAOException {
        Long id = resultSet.getLong("review_id");
        String title = resultSet.getString("title");
        Double rating = resultSet.getDouble("rating");
        String createdAt = resultSet.getTimestamp("created_at").toString();
        Long productId = resultSet.getLong("product_id");
        Product product = IProductDAO.findById(productId).orElse(null);

        Long userId = resultSet.getLong("user_id");
        User user = IUserDAO.findById(userId).orElse(null);

        return new ProductReview(id, title, rating, createdAt, product, user);
    }
}
