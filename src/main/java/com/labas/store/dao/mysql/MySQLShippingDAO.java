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

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLShippingDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Shipping WHERE shipping_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Shipping";
    private static final String INSERT = "INSERT INTO Shipping (shipping_carrier, tracking_number, shipped_at, estimated_delivery, shipping_status_id, order_id, adress_id, carrier_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Shipping SET shipping_carrier = ?, tracking_number = ?, shipped_at = ?, estimated_delivery = ?, shipping_status_id = ?, order_id = ?, adress_id = ?, carrier_id = ? WHERE shipping_id = ?";
    private static final String DELETE = "DELETE FROM Shipping WHERE shipping_id = ?";

    private final IShippingStatusDAO shippingStatusDAO;
    private final IOrderDAO orderDAO;
    private final IAddressDAO addressDAO;
    private final ICarrierDAO carrierDAO;

    public MySQLShippingDAO(IShippingStatusDAO shippingStatusDAO, IOrderDAO orderDAO, IAddressDAO addressDAO, ICarrierDAO carrierDAO) {
        this.shippingStatusDAO = shippingStatusDAO;
        this.orderDAO = orderDAO;
        this.addressDAO = addressDAO;
        this.carrierDAO = carrierDAO;
    }

    @Override
    public Optional<Shipping> findById(Long id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<Shipping> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(Shipping shipping) {
        return save(INSERT, this::setShippingParameters, shipping);
    }

    @Override
    public boolean update(Shipping shipping) {
        return update(UPDATE, this::setShippingParametersWithId, shipping);
    }

    @Override
    public boolean delete(Long id) {
        return delete(DELETE, id);
    }

    private Shipping mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("shipping_id");
            String shippingCarrier = resultSet.getString("shipping_carrier");
            String trackingNumber = resultSet.getString("tracking_number");
            Timestamp shippedAt = resultSet.getTimestamp("shipped_at");
            Date estimatedDelivery = resultSet.getDate("estimated_delivery");

            Long shippingStatusId = resultSet.getLong("shipping_status_id");
            Long orderId = resultSet.getLong("order_id");
            Long addressId = resultSet.getLong("adress_id");
            Long carrierId = resultSet.getLong("carrier_id");

            Optional<ShippingStatus> shippingStatusOptional = shippingStatusDAO.findById(shippingStatusId);
            Optional<Order> orderOptional = orderDAO.findById(orderId);
            Optional<Address> addressOptional = addressDAO.findById(addressId);
            Optional<Carrier> carrierOptional = carrierDAO.findById(carrierId);

            if (shippingStatusOptional.isEmpty() || orderOptional.isEmpty() || addressOptional.isEmpty() || carrierOptional.isEmpty()) {
                LOGGER.warn("Related entities not found for Shipping ID: {}", id);
                return null;
            }

            ShippingStatus shippingStatus = shippingStatusOptional.get();
            Order order = orderOptional.get();
            Address address = addressOptional.get();
            Carrier carrier = carrierOptional.get();

            return new Shipping(
                    id,
                    shippingCarrier,
                    trackingNumber,
                    shippedAt.toString(),
                    estimatedDelivery.toString(),
                    shippingStatus,
                    order,
                    address,
                    carrier
            );
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to Shipping object", e);
            throw new RuntimeException("Error mapping row to Shipping object", e);
        }
    }

    private void setShippingParameters(PreparedStatement statement, Shipping shipping) {
        try {
            statement.setString(1, shipping.getShippingCarrier());
            statement.setString(2, shipping.getTrackingNumber());
            statement.setTimestamp(3, Timestamp.valueOf(shipping.getShippedAt()));
            statement.setDate(4, Date.valueOf(shipping.getEstimatedDelivery()));
            statement.setLong(5, shipping.getShippingStatus().getShippingStatusId());
            statement.setLong(6, shipping.getOrder().getOrderId());
            statement.setLong(7, shipping.getAddress().getAddressId());
            statement.setLong(8, shipping.getCarrier().getCarrierId());
        } catch (SQLException e) {
            LOGGER.error("Error setting shipping parameters", e);
            throw new RuntimeException("Error setting shipping parameters", e);
        }
    }

    private void setShippingParametersWithId(PreparedStatement statement, Shipping shipping) {
        try {
            setShippingParameters(statement, shipping);
            statement.setLong(9, shipping.getShippingId());
        } catch (SQLException e) {
            LOGGER.error("Error setting shipping parameters with ID", e);
            throw new RuntimeException("Error setting shipping parameters with ID", e);
        }
    }
}