package com.labas.store.dao.jdbc;

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
public class JDBCOrderDAO extends JDBCAbstractDAO<Order, Long> implements IOrderDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCOrderDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM `Order` WHERE order_id = ?";
    private static final String FIND_ALL = "SELECT * FROM `Order`";
    private static final String INSERT = "INSERT INTO `Order` (discount, total, created_at, updated_at, order_status_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `Order` SET discount = ?, total = ?, updated_at = ?, order_status_id = ?, user_id = ? WHERE order_id = ?";
    private static final String DELETE = "DELETE FROM `Order` WHERE order_id = ?";


    private final IOrderStatusDAO IOrderStatusDAO;
    private final IUserDAO IUserDAO;

    public JDBCOrderDAO(IOrderStatusDAO IOrderStatusDAO, IUserDAO IUserDAO) {
        super();
        this.IOrderStatusDAO = IOrderStatusDAO;
        this.IUserDAO = IUserDAO;
    }

    @Override
    public Optional<Order> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding order by ID: {}", id, e);
            throw new DAOException("Error finding order by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                orders.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all orders", e);
            throw new DAOException("Error finding all orders", e);
        }

        return orders;
    }

    @Override
    public boolean save(Order order) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setFloat(1, order.getDiscount());
            statement.setFloat(2, order.getTotal());
            statement.setTimestamp(3, Timestamp.valueOf(order.getCreatedAt()));
            statement.setTimestamp(4, Timestamp.valueOf(order.getUpdatedAt()));
            statement.setLong(5, order.getOrderStatus().getOrderStatusId());
            statement.setLong(6, order.getUser().getUserId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error saving order: {}", order, e);
            throw new DAOException("Error saving order: " + order, e);
        }
    }

    @Override
    public boolean update(Order order) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setFloat(1, order.getDiscount());
            statement.setFloat(2, order.getTotal());
            statement.setTimestamp(3, Timestamp.valueOf(order.getUpdatedAt()));
            statement.setLong(4, order.getOrderStatus().getOrderStatusId());
            statement.setLong(5, order.getUser().getUserId());
            statement.setLong(6, order.getOrderId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error updating order: {}", order, e);
            throw new DAOException("Error updating order: " + order, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error deleting order by ID: {}", id, e);
            throw new DAOException("Error deleting order by ID: " + id, e);
        }
    }

    private Order mapRow(ResultSet resultSet) throws SQLException, DAOException {
        Long id = resultSet.getLong("order_id");
        Float discount = resultSet.getFloat("discount");
        Float total = resultSet.getFloat("total");
        String createdAt = resultSet.getTimestamp("created_at").toString();
        String updatedAt = resultSet.getTimestamp("updated_at").toString();

        Long orderStatusId = resultSet.getLong("order_status_id");
        OrderStatus orderStatus = IOrderStatusDAO.findById(orderStatusId).orElse(null);

        Long userId = resultSet.getLong("user_id");
        User user = IUserDAO.findById(userId).orElse(null);

        return new Order(id, discount, total, createdAt, updatedAt, orderStatus, user);
    }
}
