package com.labas.store.service.impl;

import com.labas.store.dao.IShippingDAO;
import com.labas.store.model.entities.Shipping;
import com.labas.store.service.IShippingService;

public class ShippingServiceImpl extends AbstractService<Shipping, Long> implements IShippingService {
    public ShippingServiceImpl(IShippingDAO IShippingDAO) {
        super(IShippingDAO);
    }
}
