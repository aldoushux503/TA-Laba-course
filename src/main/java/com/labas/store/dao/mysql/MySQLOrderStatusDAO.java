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
 * Implementation of OrderStatusDAO.
 */
public class MySQLOrderStatusDAO extends MySQLAbstractDAO<OrderStatus, Long> implements IOrderStatusDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLOrderStatusDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Order_status WHERE order_status_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Order_status";
    private static final String INSERT = "INSERT INTO Order_status (status_name) VALUES (?)";
    private static final String UPDATE = "UPDATE Order_status SET status_name = ? WHERE order_status_id = ?";
    private static final String DELETE = "DELETE FROM Order_status WHERE order_status_id = ?";

    @Override
    public Optional<OrderStatus> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<OrderStatus> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(OrderStatus orderStatus) {
        return save(INSERT, this::setOrderStatusParameters, orderStatus);
    }

    @Override
    public boolean update(OrderStatus orderStatus) {
        return update(UPDATE, this::setOrderStatusParametersWithId, orderStatus);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private OrderStatus mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("order_status_id");
            String statusName = resultSet.getString("status_name");
            return new OrderStatus(id, statusName);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to OrderStatus object", e);
            throw new RuntimeException("Error mapping row to OrderStatus object", e);
        }
    }

    private void setOrderStatusParameters(PreparedStatement statement, OrderStatus orderStatus) {
        try {
            statement.setString(1, orderStatus.getStatusName());
        } catch (SQLException e) {
            LOGGER.error("Error setting order status parameters", e);
            throw new RuntimeException("Error setting order status parameters", e);
        }
    }

    private void setOrderStatusParametersWithId(PreparedStatement statement, OrderStatus orderStatus) {
        try {
            setOrderStatusParameters(statement, orderStatus);
            statement.setLong(2, orderStatus.getOrderStatusId());
        } catch (SQLException e) {
            LOGGER.error("Error setting order status parameters with ID", e);
            throw new RuntimeException("Error setting order status parameters with ID", e);
        }
    }
}
