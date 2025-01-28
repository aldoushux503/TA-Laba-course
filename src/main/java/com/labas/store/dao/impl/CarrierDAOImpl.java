package com.labas.store.dao.impl;

import com.labas.store.dao.AbstractDAO;
import com.labas.store.dao.CarrierDAO;
import com.labas.store.exception.DAOException;
import com.labas.store.model.entity.Carrier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of CarrierDAO.
 */
public class CarrierDAOImpl extends AbstractDAO<Carrier, Long> implements CarrierDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarrierDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Carrier WHERE carrier_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Carrier";
    private static final String INSERT = "INSERT INTO Carrier (name, contact_number, email, website) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Carrier SET name = ?, contact_number = ?, email = ?, website = ? WHERE carrier_id = ?";
    private static final String DELETE = "DELETE FROM Carrier WHERE carrier_id = ?";

    @Override
    public Optional<Carrier> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding carrier by ID: {}", id, e);
            throw new DAOException("Error finding carrier by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Carrier> findAll() throws DAOException {
        List<Carrier> carriers = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                carriers.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all carriers", e);
            throw new DAOException("Error finding all carriers", e);
        }

        return carriers;
    }

    @Override
    public boolean save(Carrier carrier) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, carrier.getName());
            statement.setString(2, carrier.getContactNumber());
            statement.setString(3, carrier.getEmail());
            statement.setString(4, carrier.getWebsite());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error saving carrier: {}", carrier, e);
            throw new DAOException("Error saving carrier: " + carrier, e);
        }
    }

    @Override
    public boolean update(Carrier carrier) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, carrier.getName());
            statement.setString(2, carrier.getContactNumber());
            statement.setString(3, carrier.getEmail());
            statement.setString(4, carrier.getWebsite());
            statement.setLong(5, carrier.getCarrierId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error updating carrier: {}", carrier, e);
            throw new DAOException("Error updating carrier: " + carrier, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error deleting carrier by ID: {}", id, e);
            throw new DAOException("Error deleting carrier by ID: " + id, e);
        }
    }

    private Carrier mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("carrier_id");
        String name = resultSet.getString("name");
        String contactNumber = resultSet.getString("contact_number");
        String email = resultSet.getString("email");
        String website = resultSet.getString("website");

        return new Carrier(id, name, contactNumber, email, website);
    }
}
