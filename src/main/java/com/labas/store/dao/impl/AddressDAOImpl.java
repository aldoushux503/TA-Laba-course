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
 * Implementation of AddressDAO.
 */
public class AddressDAOImpl extends AbstractDAO<Address, Long> implements IAddressDAO {
    private static final Logger logger = LoggerFactory.getLogger(AddressDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Address WHERE address_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Address";
    private static final String INSERT = "INSERT INTO Address (city, street, zip_code, user_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Address SET city = ?, street = ?, zip_code = ?, user_id = ? WHERE address_id = ?";
    private static final String DELETE = "DELETE FROM Address WHERE address_id = ?";

    private final IUserDAO IUserDAO;

    public AddressDAOImpl(IUserDAO IUserDAO) {
        super();
        this.IUserDAO = IUserDAO;
    }

    @Override
    public Optional<Address> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding address by ID: {}", id, e);
            throw new DAOException("Error finding address by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Address> findAll() throws DAOException {
        List<Address> addresses = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                addresses.add(mapRow(resultSet));
            }

        } catch (SQLException e) {
            logger.error("Error finding all addresses", e);
            throw new DAOException("Error finding all addresses", e);
        }

        return addresses;
    }

    @Override
    public boolean save(Address address) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getZipCode());
            statement.setLong(4, address.getUser().getUserId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving address: {}", address, e);
            throw new DAOException("Error saving address: " + address, e);
        }
    }

    @Override
    public boolean update(Address address) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getZipCode());
            statement.setLong(4, address.getUser().getUserId());
            statement.setLong(5, address.getAddressId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating address: {}", address, e);
            throw new DAOException("Error updating address: " + address, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting address by ID: {}", id, e);
            throw new DAOException("Error deleting address by ID: " + id, e);
        }
    }

    private Address mapRow(ResultSet resultSet) throws SQLException, DAOException {
        Long id = resultSet.getLong("address_id");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        String zipCode = resultSet.getString("zip_code");
        Long userId = resultSet.getLong("user_id");
        User user = IUserDAO.findById(userId).orElse(null);

        if (user == null) {
            throw new SQLException("Failed to map Address: missing related User");
        }

        return new Address(id, city, street, zipCode, user);
    }
}
