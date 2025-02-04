package com.labas.store.service.impl;

import com.labas.store.dao.IOrderDAO;
import com.labas.store.exception.ServiceException;
import com.labas.store.model.entity.Order;
import com.labas.store.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implementation of OrderService.
 */
public class OrderServiceImpl extends AbstractService<Order, Long> implements IOrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(IOrderDAO IOrderDAO) {
        super(IOrderDAO);
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        return super.findAll();
    }
}
