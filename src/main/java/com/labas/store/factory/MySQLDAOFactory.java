package com.labas.store.factory;


import com.labas.store.dao.*;
import com.labas.store.dao.mysql.*;

public class MySQLDAOFactory implements IDAOFactory {

    @Override
    public IUserDAO createUserDAO() {
        return new MySQLUserDAO();
    }

    @Override
    public IOrderStatusDAO createOrderStatusDAO() {
        return new MySQLOrderStatusDAO();
    }

    @Override
    public IPaymentMethodDAO createPaymentMethodDAO() {
        return new MySQLPaymentMethodDAO();
    }

    @Override
    public ICarrierDAO createCarrierDAO() {
        return new MySQLCarrierDAO();
    }

    @Override
    public IAddressDAO createAddressDAO(IUserDAO userDAO) {
        return new MySQLAddressDAO(userDAO);
    }

    @Override
    public IShippingStatusDAO createShippingStatusDAO() {
        return new MySQLShippingStatusDAO();
    }

    @Override
    public IOrderDAO createOrderDAO(IOrderStatusDAO orderStatusDAO, IUserDAO userDAO) {
        return new MySQLOrderDAO(orderStatusDAO, userDAO);
    }

    @Override
    public IPaymentDAO createPaymentDAO(IPaymentMethodDAO paymentMethodDAO, IOrderDAO orderDAO, IUserDAO userDAO) {
        return new MySQLPaymentDAO(paymentMethodDAO, orderDAO, userDAO);
    }

    @Override
    public ICategoryDAO createCategoryDAO() {
        return new MySQLCategoryDAO();
    }

    @Override
    public IProductDAO createProductDAO() {
        return new MySQLProductDAO();
    }

    @Override
    public IShippingDAO createShippingDAO(
            IShippingStatusDAO shippingStatusDAO,
            IOrderDAO orderDAO,
            IAddressDAO addressDAO,
            ICarrierDAO carrierDAO
    ) {
        return new MySQLShippingDAO(shippingStatusDAO, orderDAO, addressDAO, carrierDAO);
    }

    @Override
    public IRoleDAO createRoleDAO() {
        return new MySQLRoleDAO();
    }
}