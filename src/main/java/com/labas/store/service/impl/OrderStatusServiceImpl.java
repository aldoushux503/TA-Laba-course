package com.labas.store.service.impl;

import com.labas.store.dao.GenericDAO;
import com.labas.store.dao.OrderStatusDAO;
import com.labas.store.model.entity.OrderStatus;
import com.labas.store.service.AbstractService;
import com.labas.store.service.OrderStatusService;

public class OrderStatusServiceImpl extends AbstractService<OrderStatus, Long> implements OrderStatusService {
    public OrderStatusServiceImpl(OrderStatusDAO orderStatusDAO) {
        super(orderStatusDAO);
    }
}
