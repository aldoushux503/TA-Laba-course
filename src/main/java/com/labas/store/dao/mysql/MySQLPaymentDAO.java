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
    private static final Logger logger = LoggerFactory.getLogger(MySQLPaymentDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Payment WHERE payment_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Payment";
    private static final String INSERT = "INSERT INTO Payment (created_at, updated_at, payment_method_id, user_id, order_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Payment SET updated_at = ?, payment_method_id = ?, user_id = ?, order_id = ? WHERE payment_id = ?";
    private static final String DELETE = "DELETE FROM Payment WHERE payment_id = ?";

    private final IPaymentMethodDAO IPaymentMethodDAO;
    private final IOrderDAO IOrderDAO;
    private final IUserDAO IUserDAO;

    public MySQLPaymentDAO(IPaymentMethodDAO IPaymentMethodDAO, IOrderDAO IOrderDAO, IUserDAO IUserDAO) {
        super();
        this.IPaymentMethodDAO = IPaymentMethodDAO;
        this.IOrderDAO = IOrderDAO;
        this.IUserDAO = IUserDAO;
    }

    @Override
    public Optional<Payment> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding payment by ID: {}", id, e);
            throw new DAOException("Error finding payment by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Payment> findAll() throws DAOException {
        List<Payment> payments = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                payments.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all payments", e);
            throw new DAOException("Error finding all payments", e);
        }

        return payments;
    }

    @Override
    public boolean save(Payment payment) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setTimestamp(1, Timestamp.valueOf(payment.getCreatedAt()));
            statement.setTimestamp(2, Timestamp.valueOf(payment.getUpdatedAt()));
            statement.setLong(3, payment.getPaymentMethod().getPaymentMethodId());
            statement.setLong(4, payment.getUser().getUserId());
            statement.setLong(5, payment.getOrder().getOrderId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving payment: {}", payment, e);
            throw new DAOException("Error saving payment: " + payment, e);
        }
    }

    @Override
    public boolean update(Payment payment) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setTimestamp(1, Timestamp.valueOf(payment.getUpdatedAt()));
            statement.setLong(3, payment.getPaymentMethod().getPaymentMethodId());
            statement.setLong(4, payment.getUser().getUserId());
            statement.setLong(5, payment.getOrder().getOrderId());
            statement.setLong(5, payment.getPaymentId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating payment: {}", payment, e);
            throw new DAOException("Error updating payment: " + payment, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting payment by ID: {}", id, e);
            throw new DAOException("Error deleting payment by ID: " + id, e);
        }
    }

    private Payment mapRow(ResultSet resultSet) throws SQLException, DAOException {
        Long id = resultSet.getLong("payment_id");
        String createdAt = resultSet.getTimestamp("created_at").toString();
        String updatedAt = resultSet.getTimestamp("updated_at").toString();
        Long paymentMethodId = resultSet.getLong("payment_method_id");
        PaymentMethod paymentMethod = IPaymentMethodDAO.findById(paymentMethodId).orElse(null);

        Long userId = resultSet.getLong("user_id");
        User user = IUserDAO.findById(userId).orElse(null);
        Long orderId = resultSet.getLong("order_id");
        Order order = IOrderDAO.findById(orderId).orElse(null);

        return new Payment(id, createdAt, updatedAt, paymentMethod, user, order);
    }
}
