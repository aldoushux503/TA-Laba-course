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
 * Implementation of ShippingStatusDAO.
 */
public class ShippingStatusDAOImpl extends AbstractDAO<ShippingStatus, Long> implements IShippingStatusDAO {
    private static final Logger logger = LoggerFactory.getLogger(ShippingStatusDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Shipping_status WHERE shipping_status_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Shipping_status";
    private static final String INSERT = "INSERT INTO Shipping_status (status_name) VALUES (?)";
    private static final String UPDATE = "UPDATE Shipping_status SET status_name = ? WHERE shipping_status_id = ?";
    private static final String DELETE = "DELETE FROM Shipping_status WHERE shipping_status_id = ?";

    @Override
    public Optional<ShippingStatus> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding shipping status by ID: {}", id, e);
            throw new DAOException("Error finding shipping status by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<ShippingStatus> findAll() throws DAOException {
        List<ShippingStatus> statuses = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                statuses.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all shipping statuses", e);
            throw new DAOException("Error finding all shipping statuses", e);
        }

        return statuses;
    }

    @Override
    public boolean save(ShippingStatus shippingStatus) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, shippingStatus.getStatusName());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving shipping status: {}", shippingStatus, e);
            throw new DAOException("Error saving shipping status: " + shippingStatus, e);
        }
    }

    @Override
    public boolean update(ShippingStatus shippingStatus) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, shippingStatus.getStatusName());
            statement.setLong(2, shippingStatus.getShippingStatusId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating shipping status: {}", shippingStatus, e);
            throw new DAOException("Error updating shipping status: " + shippingStatus, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting shipping status by ID: {}", id, e);
            throw new DAOException("Error deleting shipping status by ID: " + id, e);
        }
    }

    private ShippingStatus mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("shipping_status_id");
        String statusName = resultSet.getString("status_name");

        return new ShippingStatus(id, statusName);
    }
}

