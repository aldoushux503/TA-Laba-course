package com.labas.store.dao.mysql;

import com.labas.store.dao.IUserDAO;
import com.labas.store.model.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of UserDAO.
 */
public class MySQLUserDAO extends MySQLAbstractDAO<User, Long> implements IUserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLUserDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM User WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT * FROM User";
    private static final String INSERT = "INSERT INTO User (first_name, last_name, email, phone_number, password_hash) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE User SET first_name = ?, last_name = ?, email = ?, phone_number = ?, password_hash = ? WHERE user_id = ?";
    private static final String DELETE = "DELETE FROM User WHERE user_id = ?";

    @Override
    public Optional<User> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<User> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(User user) {
        return save(INSERT, this::setUserParameters, user);
    }

    @Override
    public boolean update(User user) {
        return update(UPDATE, this::setUserParametersWithId, user);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private User mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("user_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            String passwordHash = resultSet.getString("password_hash");
            return new User(id, firstName, lastName, email, phoneNumber, passwordHash);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to User object", e);
            throw new RuntimeException("Error mapping row to User object", e);
        }
    }

    private void setUserParameters(PreparedStatement statement, User user) {
        try {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getPasswordHash());
        } catch (SQLException e) {
            LOGGER.error("Error setting user parameters", e);
            throw new RuntimeException("Error setting user parameters", e);
        }
    }

    private void setUserParametersWithId(PreparedStatement statement, User user) {
        try {
            setUserParameters(statement, user);
            statement.setLong(6, user.getUserId());
        } catch (SQLException e) {
            LOGGER.error("Error setting user parameters with ID", e);
            throw new RuntimeException("Error setting user parameters with ID", e);
        }
    }
}