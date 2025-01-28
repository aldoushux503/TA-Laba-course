package com.labas.store.service.impl;

import com.labas.store.dao.ShippingDAO;
import com.labas.store.model.entity.Shipping;
import com.labas.store.service.AbstractService;
import com.labas.store.service.ShippingService;

public class ShippingServiceImpl extends AbstractService<Shipping, Long> implements ShippingService {
    public ShippingServiceImpl(ShippingDAO shippingDAO) {
        super(shippingDAO);
    }
}
