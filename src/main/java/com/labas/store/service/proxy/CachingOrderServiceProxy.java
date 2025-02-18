package com.labas.store.service.proxy;

import com.labas.store.exception.ServiceException;
import com.labas.store.model.entities.Order;
import com.labas.store.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CachingOrderServiceProxy implements IOrderService {
    private final IOrderService orderService;
    private final Map<Long, Order> cache = new HashMap<>();

    public CachingOrderServiceProxy(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public boolean save(Order order) {
        try {
            boolean result = orderService.save(order);
            if (result) {
                cache.put(order.getOrderId(), order);
            }
            return result;
        } catch (ServiceException e) {
            return false;
        }
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        try {
            if (cache.containsKey(orderId)) {
                return Optional.of(cache.get(orderId));
            }
            Optional<Order> order = orderService.findById(orderId);
            order.ifPresent(o -> cache.put(orderId, o));
            return order;
        } catch (ServiceException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findAll() {
        try {
            return orderService.findAll();
        } catch (ServiceException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean update(Order order) {
        try {
            boolean result = orderService.update(order);
            if (result) {
                cache.put(order.getOrderId(), order);
            }
            return result;
        } catch (ServiceException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long orderId) {
        try {
            boolean result = orderService.delete(orderId);
            if (result) {
                cache.remove(orderId);
            }
            return result;
        } catch (ServiceException e) {
            return false;
        }
    }
}
