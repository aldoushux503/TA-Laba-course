package com.labas.store.dao.impl;

import com.labas.store.dao.AbstractDAO;
import com.labas.store.dao.IRoleDAO;
import com.labas.store.dao.IUserDAO;
import com.labas.store.dao.IUserRoleDAO;
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
 * Implementation of UserRoleDAO.
 */
public class UserRoleDAOImpl extends AbstractDAO<UserRole, CompositeKey<Long, Long>> implements IUserRoleDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM User_role WHERE user_id = ? AND role_id = ?";
    private static final String FIND_ALL = "SELECT * FROM User_role";
    private static final String INSERT = "INSERT INTO User_role (user_id, role_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE User_role SET role_id = ? WHERE user_id = ? AND role_id = ?";
    private static final String DELETE = "DELETE FROM User_role WHERE user_id = ? AND role_id = ?";

    private final IUserDAO IUserDAO;
    private final IRoleDAO IRoleDAO;

    public UserRoleDAOImpl(IUserDAO IUserDAO, IRoleDAO IRoleDAO) {
        this.IUserDAO = IUserDAO;
        this.IRoleDAO = IRoleDAO;
    }

    @Override
    public Optional<UserRole> findById(CompositeKey<Long, Long> userRoleId) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, userRoleId.getK1());
            statement.setLong(2, userRoleId.getK2());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding user role by IDs: {}, {}", userRoleId.getK1(), userRoleId.getK2(), e);
            throw new DAOException("Error finding user role by IDs", e);
        }
        return Optional.empty();
    }

    @Override
    public List<UserRole> findAll() throws DAOException {
        List<UserRole> userRoles = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                userRoles.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all user roles", e);
            throw new DAOException("Error finding all user roles", e);
        }

        return userRoles;
    }

    @Override
    public boolean save(UserRole userRole) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setLong(1, userRole.getUser().getUserId());
            statement.setLong(2, userRole.getRole().getRoleId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving user role: {}", userRole, e);
            throw new DAOException("Error saving user role: " + userRole, e);
        }
    }

    @Override
    public boolean delete(CompositeKey<Long, Long> userRoleId) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, userRoleId.getK1());
            statement.setLong(2, userRoleId.getK2());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting user role by IDs: {}, {}", userRoleId.getK1(), userRoleId.getK2(), e);
            throw new DAOException("Error deleting user role by IDs", e);
        }
    }

    @Override
    public boolean update(UserRole userRole) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setLong(1, userRole.getRole().getRoleId());
            statement.setLong(2, userRole.getUser().getUserId());
            statement.setLong(3, userRole.getRole().getRoleId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating user role to {} and {}", userRole.getUser(), userRole.getRole(), e);
            throw new DAOException("Error updating user role", e);
        }
    }

    private UserRole mapRow(ResultSet resultSet) throws SQLException, DAOException {
        Long userId = resultSet.getLong("user_id");
        User user = IUserDAO.findById(userId).orElse(null);

        Long roleId = resultSet.getLong("role_id");
        Role role = IRoleDAO.findById(roleId).orElse(null);

        if (user == null || role == null) {
            throw new SQLException("Failed to map UserRole: missing related entities");
        }

        return new UserRole(user, role);
    }
}
