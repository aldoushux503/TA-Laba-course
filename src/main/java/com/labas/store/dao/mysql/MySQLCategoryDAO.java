package com.labas.store.dao.mysql;


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
public class MySQLCategoryDAO extends MySQLAbstractDAO<Category, Long> implements ICategoryDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLCategoryDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Category WHERE category_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Category";
    private static final String INSERT = "INSERT INTO Category (name, parent_category) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE Category SET name = ?, parent_category = ? WHERE category_id = ?";
    private static final String DELETE = "DELETE FROM Category WHERE category_id = ?";

    @Override
    public Optional<Category> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<Category> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(Category category) {
        return save(INSERT, this::setCategoryParameters, category);
    }

    @Override
    public boolean update(Category category) {
        return update(UPDATE, this::setCategoryParametersWithId, category);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private Category mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("category_id");
            String name = resultSet.getString("name");
            Long parentCategoryId = resultSet.getLong("parent_category");
            Category parentCategory = null;
            if (!resultSet.wasNull()) {
                parentCategory = findById(parentCategoryId).orElse(null);
            }
            return new Category(id, name, parentCategory);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to Category object", e);
            throw new RuntimeException("Error mapping row to Category object", e);
        }
    }

    private void setCategoryParameters(PreparedStatement statement, Category category) {
        try {
            statement.setString(1, category.getName());
            if (category.getParentCategory() != null) {
                statement.setLong(2, category.getParentCategory().getCategoryId());
            } else {
                statement.setNull(2, Types.BIGINT);
            }
        } catch (SQLException e) {
            LOGGER.error("Error setting category parameters", e);
            throw new RuntimeException("Error setting category parameters", e);
        }
    }

    private void setCategoryParametersWithId(PreparedStatement statement, Category category) {
        try {
            setCategoryParameters(statement, category);
            statement.setLong(3, category.getCategoryId());
        } catch (SQLException e) {
            LOGGER.error("Error setting category parameters with ID", e);
            throw new RuntimeException("Error setting category parameters with ID", e);
        }
    }
}
