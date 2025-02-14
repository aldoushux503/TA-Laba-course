package com.labas.store.factory;

import com.labas.store.dao.*;
import com.labas.store.dao.mybatis.MyBatisUserDAO;

public class MyBatisDAOFactory implements IGenericDAOFactory {
    @Override
    public IUserDAO createUserDAO() {
        return new MyBatisUserDAO();
    }

    @Override
    public IOrderStatusDAO createOrderStatusDAO() {
        return null;
    }

    @Override
    public IPaymentMethodDAO createPaymentMethodDAO() {
        return null;
    }

    @Override
    public ICarrierDAO createCarrierDAO() {
        return null;
    }

    @Override
    public IAddressDAO createAddressDAO(IUserDAO userDAO) {
        return null;
    }

    @Override
    public IShippingStatusDAO createShippingStatusDAO() {
        return null;
    }

    @Override
    public IOrderDAO createOrderDAO(IOrderStatusDAO orderStatusDAO, IUserDAO userDAO) {
        return null;
    }

    @Override
    public IPaymentDAO createPaymentDAO(IPaymentMethodDAO paymentMethodDAO, IOrderDAO orderDAO, IUserDAO userDAO) {
        return null;
    }

    @Override
    public ICategoryDAO createCategoryDAO() {
        return null;
    }

    @Override
    public IProductDAO createProductDAO() {
        return null;
    }

    @Override
    public IShippingDAO createShippingDAO(IShippingStatusDAO shippingStatusDAO, IOrderDAO orderDAO, IAddressDAO addressDAO, ICarrierDAO carrierDAO) {
        return null;
    }

    @Override
    public IRoleDAO createRoleDAO() {
        return null;
    }
}
