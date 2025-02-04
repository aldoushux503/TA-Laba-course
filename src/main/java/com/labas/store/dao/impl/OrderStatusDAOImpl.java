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
 * Implementation of OrderStatusDAO.
 */
public class OrderStatusDAOImpl extends AbstractDAO<OrderStatus, Long> implements IOrderStatusDAO {
    private static final Logger logger = LoggerFactory.getLogger(OrderStatusDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Order_status WHERE order_status_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Order_status";
    private static final String INSERT = "INSERT INTO Order_status (status_name) VALUES (?)";
    private static final String UPDATE = "UPDATE Order_status SET status_name = ? WHERE order_status_id = ?";
    private static final String DELETE = "DELETE FROM Order_status WHERE order_status_id = ?";

    @Override
    public Optional<OrderStatus> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding order status by ID: {}", id, e);
            throw new DAOException("Error finding order status by ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<OrderStatus> findAll() throws DAOException {
        List<OrderStatus> statuses = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                statuses.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all order statuses", e);
            throw new DAOException("Error finding all order statuses", e);
        }

        return statuses;
    }

    @Override
    public boolean save(OrderStatus orderStatus) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, orderStatus.getStatusName());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving order status: {}", orderStatus, e);
            throw new DAOException("Error saving order status: " + orderStatus, e);
        }
    }

    @Override
    public boolean update(OrderStatus orderStatus) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, orderStatus.getStatusName());
            statement.setLong(2, orderStatus.getOrderStatusId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating order status: {}", orderStatus, e);
            throw new DAOException("Error updating order status: " + orderStatus, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting order status by ID: {}", id, e);
            throw new DAOException("Error deleting order status by ID: " + id, e);
        }
    }

    private OrderStatus mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("order_status_id");
        String statusName = resultSet.getString("status_name");

        return new OrderStatus(id, statusName);
    }
}
