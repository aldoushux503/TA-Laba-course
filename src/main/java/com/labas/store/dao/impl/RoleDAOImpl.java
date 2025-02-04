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
 * Implementation of RoleDAO.
 */
public class RoleDAOImpl extends AbstractDAO<Role, Long> implements IRoleDAO {
    private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Role WHERE role_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Role";
    private static final String INSERT = "INSERT INTO Role (name) VALUES (?)";
    private static final String UPDATE = "UPDATE Role SET name = ? WHERE role_id = ?";
    private static final String DELETE = "DELETE FROM Role WHERE role_id = ?";

    @Override
    public Optional<Role> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding role by ID: {}", id, e);
            throw new DAOException("Error finding role by ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Role> findAll() throws DAOException {
        List<Role> roles = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                roles.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all roles", e);
            throw new DAOException("Error finding all roles", e);
        }

        return roles;
    }

    @Override
    public boolean save(Role role) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, role.getName());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving role: {}", role, e);
            throw new DAOException("Error saving role: " + role, e);
        }
    }

    @Override
    public boolean update(Role role) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, role.getName());
            statement.setLong(2, role.getRoleId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating role: {}", role, e);
            throw new DAOException("Error updating role: " + role, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting role by ID: {}", id, e);
            throw new DAOException("Error deleting role by ID: " + id, e);
        }
    }

    private Role mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("role_id");
        String name = resultSet.getString("name");

        return new Role(id, name);
    }
}
