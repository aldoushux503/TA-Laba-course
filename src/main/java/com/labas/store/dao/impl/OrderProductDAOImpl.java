package com.labas.store.dao.impl;

import com.labas.store.dao.*;
import com.labas.store.exception.DAOException;
import com.labas.store.model.entity.*;
import com.labas.store.util.CompositeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of OrderProductDAO.
 */
public class OrderProductDAOImpl extends AbstractDAO<OrderProduct, CompositeKey<Long, Long>> implements IOrderProductDAO {
    private static final Logger logger = LoggerFactory.getLogger(OrderProductDAOImpl.class);

    private static final String FIND_BY_ID = "SELECT * FROM Order_product WHERE order_id = ? AND product_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Order_product";
    private static final String INSERT = "INSERT INTO Order_product (order_id, product_id, price_at_order, quantity) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Order_product SET price_at_order = ?, quantity = ? WHERE order_id = ? AND product_id = ?";
    private static final String DELETE = "DELETE FROM Order_product WHERE order_id = ? AND product_id = ?";

    private final IOrderDAO IOrderDAO;
    private final IProductDAO IProductDAO;

    public OrderProductDAOImpl(IOrderDAO IOrderDAO, IProductDAO IProductDAO) {
        this.IOrderDAO = IOrderDAO;
        this.IProductDAO = IProductDAO;
    }

    @Override
    public Optional<OrderProduct> findById(CompositeKey<Long, Long> orderProductId) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, orderProductId.getK1());
            statement.setLong(2, orderProductId.getK2());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding order product by IDs: {}, {}", orderProductId.getK1(), orderProductId.getK2(), e);
            throw new DAOException("Error finding order product by IDs", e);
        }
        return Optional.empty();
    }

    @Override
    public List<OrderProduct> findAll() throws DAOException {
        List<OrderProduct> orderProducts = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                orderProducts.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding all order products", e);
            throw new DAOException("Error finding all order products", e);
        }

        return orderProducts;
    }

    @Override
    public boolean save(OrderProduct orderProduct) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setLong(1, orderProduct.getOrder().getOrderId());
            statement.setLong(2, orderProduct.getProduct().getProductId());
            statement.setFloat(3, orderProduct.getPriceAtOrder());
            statement.setInt(4, orderProduct.getQuantity());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error saving order product: {}", orderProduct, e);
            throw new DAOException("Error saving order product: " + orderProduct, e);
        }
    }

    @Override
    public boolean delete(CompositeKey<Long, Long> orderProductId) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, orderProductId.getK1());
            statement.setLong(2, orderProductId.getK2());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting order product by IDs: {}, {}", orderProductId.getK1(), orderProductId.getK2(), e);
            throw new DAOException("Error deleting order product by IDs", e);
        }
    }

    @Override
    public boolean update(OrderProduct orderProduct) throws DAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setFloat(1, orderProduct.getPriceAtOrder());
            statement.setInt(2, orderProduct.getQuantity());
            statement.setLong(3, orderProduct.getOrder().getOrderId());
            statement.setLong(4, orderProduct.getProduct().getProductId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating order product: {}", orderProduct, e);
            throw new DAOException("Error updating order product", e);
        }
    }

    private OrderProduct mapRow(ResultSet resultSet) throws SQLException, DAOException {
        Long orderId = resultSet.getLong("order_id");
        Order order = IOrderDAO.findById(orderId).orElse(null);

        Long productId = resultSet.getLong("product_id");
        Product product = IProductDAO.findById(productId).orElse(null);

        float priceAtOrder = resultSet.getFloat("price_at_order");
        int quantity = resultSet.getInt("quantity");

        if (order == null || product == null) {
            throw new SQLException("Failed to map OrderProduct: missing related entities");
        }

        return new OrderProduct(orderId, priceAtOrder, quantity, order, product);
    }
}
