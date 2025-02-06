package com.labas.store.dao.mysql;

import com.labas.store.dao.IOrderDAO;
import com.labas.store.dao.IOrderStatusDAO;
import com.labas.store.dao.IUserDAO;
import com.labas.store.exception.DAOException;
import com.labas.store.model.entity.Order;
import com.labas.store.model.entity.OrderStatus;
import com.labas.store.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of OrderDAO.
 */
public class MySQLOrderDAO extends MySQLAbstractDAO<Order, Long> implements IOrderDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLOrderDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM `Order` WHERE order_id = ?";
    private static final String FIND_ALL = "SELECT * FROM `Order`";
    private static final String INSERT = "INSERT INTO `Order` (discount, total, created_at, updated_at, order_status_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `Order` SET discount = ?, total = ?, created_at = ?, updated_at = ?, order_status_id = ?, user_id = ? WHERE order_id = ?";
    private static final String DELETE = "DELETE FROM `Order` WHERE order_id = ?";

    private final IOrderStatusDAO orderStatusDAO;
    private final IUserDAO userDAO;

    public MySQLOrderDAO(IOrderStatusDAO orderStatusDAO, IUserDAO userDAO) {
        super();
        this.orderStatusDAO = orderStatusDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<Order> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(Order order) {
        return save(INSERT, this::setOrderParameters, order);
    }

    @Override
    public boolean update(Order order) {
        return update(UPDATE, this::setOrderParametersWithId, order);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private Order mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("order_id");
            Float discount = resultSet.getFloat("discount");
            Float total = resultSet.getFloat("total");
            String createdAt = resultSet.getString("created_at");
            String updatedAt = resultSet.getString("updated_at");
            Long orderStatusId = resultSet.getLong("order_status_id");
            Long userId = resultSet.getLong("user_id");

            Optional<OrderStatus> orderStatusOptional = orderStatusDAO.findById(orderStatusId);
            Optional<User> userOptional = userDAO.findById(userId);

            if (orderStatusOptional.isEmpty() || userOptional.isEmpty()) {
                LOGGER.warn("Related entities not found for order ID: {}", id);
                return null;
            }

            OrderStatus orderStatus = orderStatusOptional.get();
            User user = userOptional.get();

            return new Order(id, discount, total, createdAt, updatedAt, orderStatus, user);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to Order object", e);
            throw new RuntimeException("Error mapping row to Order object", e);
        }
    }

    private void setOrderParameters(PreparedStatement statement, Order order) {
        try {
            statement.setFloat(1, order.getDiscount());
            statement.setFloat(2, order.getTotal());
            statement.setTimestamp(3, Timestamp.valueOf(order.getCreatedAt()));
            statement.setTimestamp(4, Timestamp.valueOf(order.getUpdatedAt()));
            statement.setLong(5, order.getOrderStatus().getOrderStatusId());
            statement.setLong(6, order.getUser().getUserId());
        } catch (SQLException e) {
            LOGGER.error("Error setting order parameters", e);
            throw new RuntimeException("Error setting order parameters", e);
        }
    }

    private void setOrderParametersWithId(PreparedStatement statement, Order order) {
        try {
            setOrderParameters(statement, order);
            statement.setLong(7, order.getOrderId());
        } catch (SQLException e) {
            LOGGER.error("Error setting order parameters with ID", e);
            throw new RuntimeException("Error setting order parameters with ID", e);
        }
    }
}