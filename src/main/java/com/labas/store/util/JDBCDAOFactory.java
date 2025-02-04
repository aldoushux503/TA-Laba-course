package com.labas.store.util;


import com.labas.store.dao.*;
import com.labas.store.dao.jdbc.*;

public class JDBCDAOFactory implements DAOFactory {

    @Override
    public IUserDAO createUserDAO() {
        return new JDBCUserDAO();
    }

    @Override
    public IOrderStatusDAO createOrderStatusDAO() {
        return new JDBCOrderStatusDAO();
    }

    @Override
    public IPaymentMethodDAO createPaymentMethodDAO() {
        return new JDBCPaymentMethodDAO();
    }

    @Override
    public ICarrierDAO createCarrierDAO() {
        return new JDBCCarrierDAO();
    }

    @Override
    public IAddressDAO createAddressDAO(IUserDAO userDAO) {
        return new JDBCAddressDAO(userDAO);
    }

    @Override
    public IShippingStatusDAO createShippingStatusDAO() {
        return new JDBCShippingStatusDAO();
    }

    @Override
    public IOrderDAO createOrderDAO(IOrderStatusDAO orderStatusDAO, IUserDAO userDAO) {
        return new JDBCOrderDAO(orderStatusDAO, userDAO);
    }

    @Override
    public IPaymentDAO createPaymentDAO(IPaymentMethodDAO paymentMethodDAO, IOrderDAO orderDAO, IUserDAO userDAO) {
        return new JDBCPaymentDAO(paymentMethodDAO, orderDAO, userDAO);
    }

    @Override
    public ICategoryDAO createCategoryDAO() {
        return new JDBCCategoryDAO();
    }

    @Override
    public IProductDAO createProductDAO() {
        return new JDBCProductDAO();
    }

    @Override
    public IShippingDAO createShippingDAO(
            IShippingStatusDAO shippingStatusDAO,
            IOrderDAO orderDAO,
            IAddressDAO addressDAO,
            ICarrierDAO carrierDAO
    ) {
        return new JDBCShippingDAO(shippingStatusDAO, orderDAO, addressDAO, carrierDAO);
    }

    @Override
    public IRoleDAO createRoleDAO() {
        return new JDBCRoleDAO();
    }
}