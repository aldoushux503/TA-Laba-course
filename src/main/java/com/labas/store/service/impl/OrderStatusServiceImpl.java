package com.labas.store.service.impl;

import com.labas.store.dao.IOrderStatusDAO;
import com.labas.store.model.entities.OrderStatus;
import com.labas.store.service.IOrderStatusService;

public class OrderStatusServiceImpl extends AbstractService<OrderStatus, Long> implements IOrderStatusService {
    public OrderStatusServiceImpl(IOrderStatusDAO IOrderStatusDAO) {
        super(IOrderStatusDAO);
    }
}
