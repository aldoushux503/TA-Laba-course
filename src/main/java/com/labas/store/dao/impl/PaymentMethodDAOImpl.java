package com.labas.store.dao.impl;

import com.labas.store.dao.AbstractDAO;
import com.labas.store.dao.PaymentMethodDAO;
import com.labas.store.exception.DAOException;
import com.labas.store.model.entity.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of PaymentMethodDAO.
 */
public class PaymentMethodDAOImpl extends AbstractDAO<PaymentMethod, Long> implements PaymentMethodDAO {
    private static final Logger logger = LoggerFactory.getLogger(PaymentMethodDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Payment_method WHERE payment_method_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Payment_method";
    private static final String INSERT = "INSERT INTO Payment_method (name) VALUES (?)";
    private static final String UPDATE = "UPDATE Payment_method SET name = ? WHERE payment_method_id = ?";
    private static final String DELETE = "DELETE FROM Payment_method WHERE payment_method_id = ?";

    @Override
    public Optional<PaymentMethod> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding payment method by ID: {}", id, e);
            throw new DAOException("Error finding payment method by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<PaymentMethod> findAll() throws DAOException {
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                paymentMethods.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all payment methods", e);
            throw new DAOException("Error finding all payment methods", e);
        }

        return paymentMethods;
    }

    @Override
    public boolean save(PaymentMethod paymentMethod) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, paymentMethod.getName());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving payment method: {}", paymentMethod, e);
            throw new DAOException("Error saving payment method: " + paymentMethod, e);
        }
    }

    @Override
    public boolean update(PaymentMethod paymentMethod) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, paymentMethod.getName());
            statement.setLong(2, paymentMethod.getPaymentMethodId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating payment method: {}", paymentMethod, e);
            throw new DAOException("Error updating payment method: " + paymentMethod, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting payment method by ID: {}", id, e);
            throw new DAOException("Error deleting payment method by ID: " + id, e);
        }
    }

    private PaymentMethod mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("payment_method_id");
        String name = resultSet.getString("name");

        return new PaymentMethod(id, name);
    }
}
