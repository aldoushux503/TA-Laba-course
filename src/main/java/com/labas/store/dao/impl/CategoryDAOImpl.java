package com.labas.store.dao.impl;


import com.labas.store.dao.AbstractDAO;
import com.labas.store.dao.ICategoryDAO;
import com.labas.store.exception.DAOException;
import com.labas.store.model.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of CategoryDAO.
 */
public class CategoryDAOImpl extends AbstractDAO<Category, Long> implements ICategoryDAO {
    private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Category WHERE category_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Category";
    private static final String INSERT = "INSERT INTO Category (name, parent_category) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE Category SET name = ?, parent_category = ? WHERE category_id = ?";
    private static final String DELETE = "DELETE FROM Category WHERE category_id = ?";

    @Override
    public Optional<Category> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding category by ID: {}", id, e);
            throw new DAOException("Error finding category by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Category> findAll() throws DAOException {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                categories.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all categories", e);
            throw new DAOException("Error finding all categories", e);
        }

        return categories;
    }

    @Override
    public boolean save(Category category) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, category.getName());
            if (category.getParentCategory() != null) {
                statement.setLong(2, category.getParentCategory().getCategoryId());
            } else {
                statement.setNull(2, Types.BIGINT);
            }

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving category: {}", category, e);
            throw new DAOException("Error saving category: " + category, e);
        }
    }

    @Override
    public boolean update(Category category) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, category.getName());
            if (category.getParentCategory() != null) {
                statement.setLong(2, category.getParentCategory().getCategoryId());
            } else {
                statement.setNull(2, Types.BIGINT);
            }
            statement.setLong(3, category.getCategoryId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating category: {}", category, e);
            throw new DAOException("Error updating category: " + category, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting category by ID: {}", id, e);
            throw new DAOException("Error deleting category by ID: " + id, e);
        }
    }

    private Category mapRow(ResultSet resultSet) throws SQLException, DAOException {
        Long id = resultSet.getLong("category_id");
        String name = resultSet.getString("name");
        Long parentCategoryId = resultSet.getLong("parent_category");
        Category parentCategory = null;
        if (!resultSet.wasNull()) {
            parentCategory = this.findById(parentCategoryId).orElse(null);
        }

        return new Category(id, name, parentCategory);
    }
}
