package com.labas.store.factory;

import com.labas.store.dao.*;
import com.labas.store.dao.mybatis.*;

public class MyBatisDAOFactory implements IGenericDAOFactory {
    @Override
    public IUserDAO createUserDAO() {
        return new MyBatisUserDAO();
    }

    @Override
    public IOrderStatusDAO createOrderStatusDAO() {
        return new MyBatisOrderStatusDAO();
    }

    @Override
    public IPaymentMethodDAO createPaymentMethodDAO() {
        return new MyBatisPaymentMethodDAO();
    }

    @Override
    public ICarrierDAO createCarrierDAO() {
        return new MyBatisCarrierDAO();
    }

    @Override
    public IAddressDAO createAddressDAO(IUserDAO userDAO) {
        return new MyBatisAddressDAO(userDAO);
    }

    @Override
    public IShippingStatusDAO createShippingStatusDAO() {
        return new MyBatisShippingStatusDAO();
    }

    @Override
    public IOrderDAO createOrderDAO(IOrderStatusDAO orderStatusDAO, IUserDAO userDAO) {
        return new MyBatisOrderDAO(orderStatusDAO, userDAO);
    }


    @Override
    public IPaymentDAO createPaymentDAO(IPaymentMethodDAO paymentMethodDAO, IOrderDAO orderDAO, IUserDAO userDAO) {
        return new MyBatisPaymentDAO(paymentMethodDAO, orderDAO, userDAO);
    }

    @Override
    public ICategoryDAO createCategoryDAO() {
        return new MyBatisCategoryDAO();
    }

    @Override
    public IProductDAO createProductDAO() {
        return new MyBatisProductDAO();
    }

    @Override
    public IShippingDAO createShippingDAO(IShippingStatusDAO shippingStatusDAO, IOrderDAO orderDAO, IAddressDAO addressDAO, ICarrierDAO carrierDAO) {
        return new MyBatisShippingDAO(shippingStatusDAO, orderDAO, addressDAO, carrierDAO);
    }

    @Override
    public IRoleDAO createRoleDAO() {
        return new MyBatisRoleDAO();
    }
}
