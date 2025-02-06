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
 * Implementation of RoleDAO.
 */
public class MySQLRoleDAO extends MySQLAbstractDAO<Role, Long> implements IRoleDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLRoleDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Role WHERE role_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Role";
    private static final String INSERT = "INSERT INTO Role (name) VALUES (?)";
    private static final String UPDATE = "UPDATE Role SET name = ? WHERE role_id = ?";
    private static final String DELETE = "DELETE FROM Role WHERE role_id = ?";

    @Override
    public Optional<Role> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<Role> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(Role role) {
        return save(INSERT, this::setRoleParameters, role);
    }

    @Override
    public boolean update(Role role) {
        return update(UPDATE, this::setRoleParametersWithId, role);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private Role mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("role_id");
            String name = resultSet.getString("name");
            return new Role(id, name);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to Role object", e);
            throw new RuntimeException("Error mapping row to Role object", e);
        }
    }

    private void setRoleParameters(PreparedStatement statement, Role role) {
        try {
            statement.setString(1, role.getName());
        } catch (SQLException e) {
            LOGGER.error("Error setting role parameters", e);
            throw new RuntimeException("Error setting role parameters", e);
        }
    }

    private void setRoleParametersWithId(PreparedStatement statement, Role role) {
        try {
            statement.setString(1, role.getName());
            statement.setLong(2, role.getRoleId());
        } catch (SQLException e) {
            LOGGER.error("Error setting role parameters with ID", e);
            throw new RuntimeException("Error setting role parameters with ID", e);
        }
    }
}