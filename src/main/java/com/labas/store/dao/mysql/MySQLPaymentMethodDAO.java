package com.labas.store.dao.mysql;

import com.labas.store.dao.IPaymentMethodDAO;
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
public class MySQLPaymentMethodDAO extends MySQLAbstractDAO<PaymentMethod, Long> implements IPaymentMethodDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLPaymentMethodDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Payment_method WHERE payment_method_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Payment_method";
    private static final String INSERT = "INSERT INTO Payment_method (name) VALUES (?)";
    private static final String UPDATE = "UPDATE Payment_method SET name = ? WHERE payment_method_id = ?";
    private static final String DELETE = "DELETE FROM Payment_method WHERE payment_method_id = ?";

    @Override
    public Optional<PaymentMethod> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<PaymentMethod> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(PaymentMethod paymentMethod) {
        return save(INSERT, this::setPaymentMethodParameters, paymentMethod);
    }

    @Override
    public boolean update(PaymentMethod paymentMethod) {
        return update(UPDATE, this::setPaymentMethodParametersWithId, paymentMethod);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private PaymentMethod mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("payment_method_id");
            String name = resultSet.getString("name");
            return new PaymentMethod(id, name);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to PaymentMethod object", e);
            throw new RuntimeException("Error mapping row to PaymentMethod object", e);
        }
    }

    private void setPaymentMethodParameters(PreparedStatement statement, PaymentMethod paymentMethod) {
        try {
            statement.setString(1, paymentMethod.getName());
        } catch (SQLException e) {
            LOGGER.error("Error setting payment method parameters", e);
            throw new RuntimeException("Error setting payment method parameters", e);
        }
    }

    private void setPaymentMethodParametersWithId(PreparedStatement statement, PaymentMethod paymentMethod) {
        try {
            statement.setString(1, paymentMethod.getName());
            statement.setLong(2, paymentMethod.getPaymentMethodId());
        } catch (SQLException e) {
            LOGGER.error("Error setting payment method parameters with ID", e);
            throw new RuntimeException("Error setting payment method parameters with ID", e);
        }
    }
}