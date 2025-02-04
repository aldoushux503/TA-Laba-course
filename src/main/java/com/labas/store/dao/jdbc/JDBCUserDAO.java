package com.labas.store.dao.jdbc;

import com.labas.store.dao.IUserDAO;
import com.labas.store.exception.DAOException;
import com.labas.store.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of UserDAO.
 */
public class JDBCUserDAO extends JDBCAbstractDAO<User, Long> implements IUserDAO {
    private static final Logger logger = LoggerFactory.getLogger(JDBCUserDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM User WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT * FROM User";
    private static final String INSERT = "INSERT INTO User (first_name, last_name, email, phone_number, password_hash) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE User SET first_name = ?, last_name = ?, email = ?, phone_number = ?, password_hash = ? WHERE user_id = ?";
    private static final String DELETE = "DELETE FROM User WHERE user_id = ?";

    @Override
    public Optional<User> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding user by ID: {}", id, e);
            throw new DAOException("Error finding user by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                users.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all users", e);
            throw new DAOException("Error finding all users", e);
        }

        return users;
    }

    @Override
    public boolean save(User user) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getPasswordHash());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving user: {}", user, e);
            throw new DAOException("Error saving user: " + user, e);
        }
    }

    @Override
    public boolean update(User user) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getPasswordHash());
            statement.setLong(6, user.getUserId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating user: {}", user, e);
            throw new DAOException("Error updating user: " + user, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting user by ID: {}", id, e);
            throw new DAOException("Error deleting user by ID: " + id, e);
        }
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phone_number");
        String passwordHash = resultSet.getString("password_hash");

        return new User(id, firstName, lastName, email, phoneNumber, passwordHash);
    }
}
