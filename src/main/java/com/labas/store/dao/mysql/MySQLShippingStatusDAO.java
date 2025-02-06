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
 * Implementation of ShippingStatusDAO.
 */
public class MySQLShippingStatusDAO extends MySQLAbstractDAO<ShippingStatus, Long> implements IShippingStatusDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLShippingStatusDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Shipping_status WHERE shipping_status_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Shipping_status";
    private static final String INSERT = "INSERT INTO Shipping_status (status_name) VALUES (?)";
    private static final String UPDATE = "UPDATE Shipping_status SET status_name = ? WHERE shipping_status_id = ?";
    private static final String DELETE = "DELETE FROM Shipping_status WHERE shipping_status_id = ?";

    @Override
    public Optional<ShippingStatus> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<ShippingStatus> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(ShippingStatus shippingStatus) {
        return save(INSERT, this::setShippingStatusParameters, shippingStatus);
    }

    @Override
    public boolean update(ShippingStatus shippingStatus) {
        return update(UPDATE, this::setShippingStatusParametersWithId, shippingStatus);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private ShippingStatus mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("shipping_status_id");
            String statusName = resultSet.getString("status_name");
            return new ShippingStatus(id, statusName);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to ShippingStatus object", e);
            throw new RuntimeException("Error mapping row to ShippingStatus object", e);
        }
    }

    private void setShippingStatusParameters(PreparedStatement statement, ShippingStatus shippingStatus) {
        try {
            statement.setString(1, shippingStatus.getStatusName());
        } catch (SQLException e) {
            LOGGER.error("Error setting shipping status parameters", e);
            throw new RuntimeException("Error setting shipping status parameters", e);
        }
    }

    private void setShippingStatusParametersWithId(PreparedStatement statement, ShippingStatus shippingStatus) {
        try {
            statement.setString(1, shippingStatus.getStatusName());
            statement.setLong(2, shippingStatus.getShippingStatusId());
        } catch (SQLException e) {
            LOGGER.error("Error setting shipping status parameters with ID", e);
            throw new RuntimeException("Error setting shipping status parameters with ID", e);
        }
    }
}