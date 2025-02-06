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
 * Implementation of ShippingDAO.
 */
public class MySQLShippingDAO extends MySQLAbstractDAO<Shipping, Long> implements IShippingDAO {
    private static final Logger logger = LoggerFactory.getLogger(MySQLShippingDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Shipping WHERE shipping_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Shipping";
    private static final String INSERT = "INSERT INTO Shipping (shipping_carrier, tracking_number, shipped_at, estimated_delivery, shipping_status_id, order_id, adress_id, carrier_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Shipping SET shipping_carrier = ?, tracking_number = ?, shipped_at = ?, estimated_delivery = ?, shipping_status_id = ?, order_id = ?, adress_id = ?, carrier_id = ? WHERE shipping_id = ?";
    private static final String DELETE = "DELETE FROM Shipping WHERE shipping_id = ?";

    private final IShippingStatusDAO IShippingStatusDAO;
    private final IOrderDAO IOrderDAO;
    private final IAddressDAO IAddressDAO;
    private final ICarrierDAO ICarrierDAO;

    public MySQLShippingDAO(IShippingStatusDAO IShippingStatusDAO, IOrderDAO IOrderDAO, IAddressDAO IAddressDAO, ICarrierDAO ICarrierDAO) {
        super();
        this.IShippingStatusDAO = IShippingStatusDAO;
        this.IOrderDAO = IOrderDAO;
        this.IAddressDAO = IAddressDAO;
        this.ICarrierDAO = ICarrierDAO;
    }

    @Override
    public Optional<Shipping> findById(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding shipping by ID: {}", id, e);
            throw new DAOException("Error finding shipping by ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Shipping> findAll() throws DAOException {
        List<Shipping> result = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                result.add(mapRow(resultSet));
            }

        } catch (SQLException e) {
            logger.error("Error finding all shipping", e);
            throw new DAOException("Error finding all shipping", e);
        }
        return result;
    }

    @Override
    public boolean save(Shipping shipping) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, shipping.getShippingCarrier());
            statement.setString(2, shipping.getTrackingNumber());
            statement.setTimestamp(3, Timestamp.valueOf(shipping.getShippedAt()));
            statement.setDate(4, Date.valueOf(shipping.getEstimatedDelivery()));
            statement.setLong(5, shipping.getShippingStatus().getShippingStatusId());
            statement.setLong(6, shipping.getOrder().getOrderId());
            statement.setLong(7, shipping.getAddress().getAddressId());
            statement.setLong(8, shipping.getCarrier().getCarrierId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving shipping: {}", shipping, e);
            throw new DAOException("Error saving shipping: " + shipping, e);
        }
    }

    @Override
    public boolean update(Shipping shipping) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, shipping.getShippingCarrier());
            statement.setString(2, shipping.getTrackingNumber());
            statement.setTimestamp(3, Timestamp.valueOf(shipping.getShippedAt()));
            statement.setDate(4, Date.valueOf(shipping.getEstimatedDelivery()));
            statement.setLong(5, shipping.getShippingStatus().getShippingStatusId());
            statement.setLong(6, shipping.getOrder().getOrderId());
            statement.setLong(7, shipping.getAddress().getAddressId());
            statement.setLong(8, shipping.getCarrier().getCarrierId());
            statement.setLong(9, shipping.getShippingId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating shipping: {}", shipping, e);
            throw new DAOException("Error updating shipping: " + shipping, e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting shipping by ID: {}", id, e);
            throw new DAOException("Error deleting shipping by ID: " + id, e);
        }
    }

    private Shipping mapRow(ResultSet resultSet) throws SQLException, DAOException {
        Long id = resultSet.getLong("shipping_id");
        String shippingCarrier = resultSet.getString("shipping_carrier");
        String trackingNumber = resultSet.getString("tracking_number");
        String shippedAt = resultSet.getTimestamp("shipped_at").toString();
        String estimatedDelivery = resultSet.getDate("estimated_delivery").toString();

        Long shippingStatusId = resultSet.getLong("shipping_status_id");
        ShippingStatus shippingStatus = IShippingStatusDAO.findById(shippingStatusId).orElse(null);

        Long orderId = resultSet.getLong("order_id");
        Order order = IOrderDAO.findById(orderId).orElse(null);

        Long adressId = resultSet.getLong("adress_id");
        Address address = IAddressDAO.findById(adressId).orElse(null);

        Long carrierId = resultSet.getLong("carrier_id");
        Carrier carrier = ICarrierDAO.findById(carrierId).orElse(null);

        if (shippingStatus == null || order == null || address == null || carrier == null) {
            throw new SQLException("Failed to map Shipping: missing related entities");
        }

        return new Shipping(id, shippingCarrier, trackingNumber, shippedAt, estimatedDelivery, shippingStatus, order, address, carrier);
    }
}
