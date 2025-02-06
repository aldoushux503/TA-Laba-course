package com.labas.store.dao.mysql;

import com.labas.store.dao.*;

import com.labas.store.model.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of AddressDAO.
 */
public class MySQLAddressDAO extends MySQLAbstractDAO<Address, Long> implements IAddressDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLAddressDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Address WHERE address_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Address";
    private static final String INSERT = "INSERT INTO Address (city, street, zip_code, user_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Address SET city = ?, street = ?, zip_code = ?, user_id = ? WHERE address_id = ?";
    private static final String DELETE = "DELETE FROM Address WHERE address_id = ?";

    private final IUserDAO userDAO;

    public MySQLAddressDAO(IUserDAO userDAO) {
        super();
        this.userDAO = userDAO;
    }

    @Override
    public Optional<Address> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<Address> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(Address address) {
        return save(INSERT, this::setAddressParameters, address);
    }

    @Override
    public boolean update(Address address) {
        return update(UPDATE, this::setAddressParametersWithId, address);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private Address mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("address_id");
            String city = resultSet.getString("city");
            String street = resultSet.getString("street");
            String zipCode = resultSet.getString("zip_code");
            Long userId = resultSet.getLong("user_id");

            Optional<User> userOptional = userDAO.findById(userId);
            if (userOptional.isEmpty()) {
                LOGGER.warn("Related User not found for Address ID: {}", id);
                return null;
            }

            User user = userOptional.get();
            return new Address(id, city, street, zipCode, user);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to Address object", e);
            throw new RuntimeException("Error mapping row to Address object", e);
        }
    }

    private void setAddressParameters(PreparedStatement statement, Address address) {
        try {
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getZipCode());
            statement.setLong(4, address.getUser().getUserId());
        } catch (SQLException e) {
            LOGGER.error("Error setting address parameters", e);
            throw new RuntimeException("Error setting address parameters", e);
        }
    }

    private void setAddressParametersWithId(PreparedStatement statement, Address address) {
        try {
            setAddressParameters(statement, address);
            statement.setLong(5, address.getAddressId());
        } catch (SQLException e) {
            LOGGER.error("Error setting address parameters with ID", e);
            throw new RuntimeException("Error setting address parameters with ID", e);
        }
    }
}
