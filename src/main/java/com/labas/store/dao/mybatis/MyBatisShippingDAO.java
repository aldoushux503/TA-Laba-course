package com.labas.store.dao.mybatis;

import com.labas.store.dao.*;
import com.labas.store.mapper.IShippingMapper;
import com.labas.store.model.entities.Shipping;

public class MyBatisShippingDAO extends MyBatisAbstractDAO<Shipping, Long> implements IShippingDAO {

    private IShippingStatusDAO shippingStatusDAO;
    private IOrderDAO orderDAO;
    private IAddressDAO addressDAO;
    private ICarrierDAO carrierDAO;

    public MyBatisShippingDAO(IShippingStatusDAO shippingStatusDAO, IOrderDAO orderDAO, IAddressDAO addressDAO, ICarrierDAO carrierDAO) {
        super();
        this.shippingStatusDAO = shippingStatusDAO;
        this.orderDAO = orderDAO;
        this.addressDAO = addressDAO;
        this.carrierDAO = carrierDAO;
    }

    @Override
    protected Class<?> getMapperClass() {
        return IShippingMapper.class;
    }
}
