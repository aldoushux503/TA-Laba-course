package com.labas.store.dao.mysql;

import com.labas.store.dao.ICarrierDAO;
import com.labas.store.model.entities.Carrier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of CarrierDAO.
 */
public class MySQLCarrierDAO extends MySQLAbstractDAO<Carrier, Long> implements ICarrierDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLCarrierDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Carrier WHERE carrier_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Carrier";
    private static final String INSERT = "INSERT INTO Carrier (name, contact_number, email, website) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Carrier SET name = ?, contact_number = ?, email = ?, website = ? WHERE carrier_id = ?";
    private static final String DELETE = "DELETE FROM Carrier WHERE carrier_id = ?";

    @Override
    public Optional<Carrier> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<Carrier> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(Carrier carrier) {
        return save(INSERT, this::setCarrierParameters, carrier);
    }

    @Override
    public boolean update(Carrier carrier) {
        return update(UPDATE, this::setCarrierParametersWithId, carrier);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private Carrier mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("carrier_id");
            String name = resultSet.getString("name");
            String contactNumber = resultSet.getString("contact_number");
            String email = resultSet.getString("email");
            String website = resultSet.getString("website");
            return new Carrier(id, name, contactNumber, email, website);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row", e);
            throw new RuntimeException(e);
        }
    }


    private void setCarrierParameters(PreparedStatement statement, Carrier carrier) {
        try {
            statement.setString(1, carrier.getName());
            statement.setString(2, carrier.getContactNumber());
            statement.setString(3, carrier.getEmail());
            statement.setString(4, carrier.getWebsite());
        } catch (SQLException e) {
            LOGGER.error("Error set carrier parameters", e);
            throw new RuntimeException(e);
        }
    }

    private void setCarrierParametersWithId(PreparedStatement statement, Carrier carrier) {
        try{
        setCarrierParameters(statement, carrier);
        statement.setLong(5, carrier.getCarrierId());
        } catch (SQLException e) {
            LOGGER.error("Error set carrier parameters with id", e);
            throw new RuntimeException(e);
        }
    }
}
