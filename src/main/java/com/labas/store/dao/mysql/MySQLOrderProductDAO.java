package com.labas.store.dao.mysql;

import com.labas.store.dao.*;
import com.labas.store.model.entities.*;
import com.labas.store.util.CompositeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of OrderProductDAO.
 */
public class MySQLOrderProductDAO extends MySQLAbstractDAO<OrderProduct, CompositeKey<Long, Long>> implements IOrderProductDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLOrderProductDAO.class);

    private static final String FIND_BY_ID = "SELECT * FROM Order_product WHERE order_id = ? AND product_id = ?";
    private static final String FIND_ALL = "SELECT * FROM Order_product";
    private static final String INSERT = "INSERT INTO Order_product (order_id, product_id, price_at_order, quantity) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Order_product SET price_at_order = ?, quantity = ? WHERE order_id = ? AND product_id = ?";
    private static final String DELETE = "DELETE FROM Order_product WHERE order_id = ? AND product_id = ?";

    private final IOrderDAO orderDAO;
    private final IProductDAO productDAO;

    public MySQLOrderProductDAO(IOrderDAO orderDAO, IProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }

    @Override
    public Optional<OrderProduct> findById(CompositeKey<Long, Long> id) {
        return findById(FIND_BY_ID, id, this::mapRow);
    }

    @Override
    public List<OrderProduct> findAll() {
        return findAll(FIND_ALL, this::mapRow);
    }

    @Override
    public boolean save(OrderProduct orderProduct) {
        return save(INSERT, this::setOrderProductParameters, orderProduct);
    }

    @Override
    public boolean update(OrderProduct orderProduct) {
        return update(UPDATE, this::setOrderProductParametersWithId, orderProduct);
    }

    @Override
    public boolean delete(CompositeKey<Long, Long> id) {
        return delete(DELETE, id);
    }

    private OrderProduct mapRow(ResultSet resultSet) {
        try {
            Long orderId = resultSet.getLong("order_id");
            Long productId = resultSet.getLong("product_id");

            // Получаем связанные сущности
            Optional<Order> orderOptional = orderDAO.findById(orderId);
            Optional<Product> productOptional = productDAO.findById(productId);

            if (orderOptional.isEmpty() || productOptional.isEmpty()) {
                LOGGER.warn("Related entities not found for OrderProduct: order ID={}, product ID={}", orderId, productId);
                return null; // Можно выбросить исключение или вернуть null в зависимости от требований
            }

            Order order = orderOptional.get();
            Product product = productOptional.get();

            float priceAtOrder = resultSet.getFloat("price_at_order");
            int quantity = resultSet.getInt("quantity");

            return new OrderProduct(orderId, priceAtOrder, quantity, order, product);
        } catch (SQLException e) {
            LOGGER.error("Error mapping row to OrderProduct object", e);
            throw new RuntimeException("Error mapping row to OrderProduct object", e);
        }
    }

    private void setOrderProductParameters(PreparedStatement statement, OrderProduct orderProduct) {
        try {
            statement.setLong(1, orderProduct.getOrder().getOrderId());
            statement.setLong(2, orderProduct.getProduct().getProductId());
            statement.setFloat(3, orderProduct.getPriceAtOrder());
            statement.setInt(4, orderProduct.getQuantity());
        } catch (SQLException e) {
            LOGGER.error("Error setting order product parameters", e);
            throw new RuntimeException("Error setting order product parameters", e);
        }
    }

    private void setOrderProductParametersWithId(PreparedStatement statement, OrderProduct orderProduct) {
        try {
            statement.setFloat(1, orderProduct.getPriceAtOrder());
            statement.setInt(2, orderProduct.getQuantity());
            statement.setLong(3, orderProduct.getOrder().getOrderId());
            statement.setLong(4, orderProduct.getProduct().getProductId());
        } catch (SQLException e) {
            LOGGER.error("Error setting order product parameters with ID", e);
            throw new RuntimeException("Error setting order product parameters with ID", e);
        }
    }
}