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
 * Implementation of PaymentDAO.
 */
public class MySQLPaymentDAO extends MySQLAbstractDAO<Payment, Long> implements IPaymentDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLPaymentDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Payment WHERE payment_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Payment";
    private static final String INSERT = "INSERT INTO Payment (created_at, updated_at, payment_method_id, user_id, order_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Payment SET updated_at = ?, payment_method_id = ?, user_id = ?, order_id = ? WHERE payment_id = ?";
    private static final String DELETE = "DELETE FROM Payment WHERE payment_id = ?";

    private final IPaymentMethodDAO paymentMethodDAO;
    private final IOrderDAO orderDAO;
    private final IUserDAO userDAO;

    public MySQLPaymentDAO(IPaymentMethodDAO paymentMethodDAO, IOrderDAO orderDAO, IUserDAO userDAO) {
        this.paymentMethodDAO = paymentMethodDAO;
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<Payment> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(Payment payment) {
        return save(INSERT, this::setPaymentParameters, payment);
    }

    @Override
    public boolean update(Payment payment) {
        return update(UPDATE, this::setPaymentParametersWithId, payment);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private Payment mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("payment_id");
            String createdAt = resultSet.getString("created_at");
            String updatedAt = resultSet.getString("updated_at");
            Long paymentMethodId = resultSet.getLong("payment_method_id");
            Long userId = resultSet.getLong("user_id");
            Long orderId = resultSet.getLong("order_id");


            Optional<PaymentMethod> paymentMethodOptional = paymentMethodDAO.findById(paymentMethodId);
            Optional<User> userOptional = userDAO.findById(userId);
            Optional<Order> orderOptional = orderDAO.findById(orderId);

            if (paymentMethodOptional.isEmpty() || userOptional.isEmpty() || orderOptional.isEmpty()) {
                LOGGER.warn("Related entities not found for Payment ID: {}", id);
                return null;
            }

            PaymentMethod paymentMethod = paymentMethodOptional.get();
            User user = userOptional.get();
            Order order = orderOptional.get();

            return new Payment(id, createdAt, updatedAt, paymentMethod, user, order);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to Payment object", e);
            throw new RuntimeException("Error mapping row to Payment object", e);
        }
    }

    private void setPaymentParameters(PreparedStatement statement, Payment payment) {
        try {
            statement.setTimestamp(1, Timestamp.valueOf(payment.getCreatedAt()));
            statement.setTimestamp(2, Timestamp.valueOf(payment.getUpdatedAt()));
            statement.setLong(3, payment.getPaymentMethod().getPaymentMethodId());
            statement.setLong(4, payment.getUser().getUserId());
            statement.setLong(5, payment.getOrder().getOrderId());
        } catch (SQLException e) {
            LOGGER.error("Error setting payment parameters", e);
            throw new RuntimeException("Error setting payment parameters", e);
        }
    }

    private void setPaymentParametersWithId(PreparedStatement statement, Payment payment) {
        try {
            statement.setTimestamp(1, Timestamp.valueOf(payment.getUpdatedAt()));
            statement.setLong(2, payment.getPaymentMethod().getPaymentMethodId());
            statement.setLong(3, payment.getUser().getUserId());
            statement.setLong(4, payment.getOrder().getOrderId());
            statement.setLong(5, payment.getPaymentId());
        } catch (SQLException e) {
            LOGGER.error("Error setting payment parameters with ID", e);
            throw new RuntimeException("Error setting payment parameters with ID", e);
        }
    }
}