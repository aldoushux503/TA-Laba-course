package com.labas.store.dao.mysql;

import com.labas.store.dao.IRoleDAO;
import com.labas.store.dao.IUserDAO;
import com.labas.store.dao.IUserRoleDAO;
import com.labas.store.model.entities.*;
import com.labas.store.util.CompositeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of UserRoleDAO.
 */
public class MySQLUserRoleDAO extends MySQLAbstractDAO<UserRole, CompositeKey<Long, Long>> implements IUserRoleDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLUserRoleDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM User_role WHERE user_id = ? AND role_id = ?";
    private static final String FIND_ALL = "SELECT * FROM User_role";
    private static final String INSERT = "INSERT INTO User_role (user_id, role_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE User_role SET role_id = ? WHERE user_id = ? AND role_id = ?";
    private static final String DELETE = "DELETE FROM User_role WHERE user_id = ? AND role_id = ?";

    private final IUserDAO userDAO;
    private final IRoleDAO roleDAO;

    public MySQLUserRoleDAO(IUserDAO userDAO, IRoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public Optional<UserRole> findById(CompositeKey<Long, Long> id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<UserRole> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(UserRole userRole) {
        return save(INSERT, this::setUserRoleParameters, userRole);
    }

    @Override
    public boolean update(UserRole userRole) {
        return update(UPDATE, this::setUserRoleParametersWithId, userRole);
    }

    @Override
    public boolean delete(CompositeKey<Long, Long> id) {
        return delete(DELETE, id);
    }

    private UserRole mapRow(ResultSet resultSet) {
        try {
            Long userId = resultSet.getLong("user_id");
            Long roleId = resultSet.getLong("role_id");

            Optional<User> userOptional = userDAO.findById(userId);
            Optional<Role> roleOptional = roleDAO.findById(roleId);

            if (userOptional.isEmpty() || roleOptional.isEmpty()) {
                LOGGER.warn("Related entities not found for UserRole: user ID={}, role ID={}", userId, roleId);
                return null;
            }

            User user = userOptional.get();
            Role role = roleOptional.get();

            return new UserRole(user, role);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to UserRole object", e);
            throw new RuntimeException("Error mapping row to UserRole object", e);
        }
    }

    private void setUserRoleParameters(PreparedStatement statement, UserRole userRole) {
        try {
            statement.setLong(1, userRole.getUser().getUserId());
            statement.setLong(2, userRole.getRole().getRoleId());
        } catch (SQLException e) {
            LOGGER.error("Error setting user role parameters", e);
            throw new RuntimeException("Error setting user role parameters", e);
        }
    }

    private void setUserRoleParametersWithId(PreparedStatement statement, UserRole userRole) {
        try {
            statement.setLong(1, userRole.getRole().getRoleId());
            statement.setLong(2, userRole.getUser().getUserId());
            statement.setLong(3, userRole.getRole().getRoleId());
        } catch (SQLException e) {
            LOGGER.error("Error setting user role parameters with ID", e);
            throw new RuntimeException("Error setting user role parameters with ID", e);
        }
    }
}
