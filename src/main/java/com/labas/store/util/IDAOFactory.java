package com.labas.store.util;

import com.labas.store.dao.*;

public interface IDAOFactory {
    IUserDAO createUserDAO();
    IOrderStatusDAO createOrderStatusDAO();
    IPaymentMethodDAO createPaymentMethodDAO();
    ICarrierDAO createCarrierDAO();
    IAddressDAO createAddressDAO(IUserDAO userDAO);
    IShippingStatusDAO createShippingStatusDAO();
    IOrderDAO createOrderDAO(IOrderStatusDAO orderStatusDAO, IUserDAO userDAO);
    IPaymentDAO createPaymentDAO(IPaymentMethodDAO paymentMethodDAO, IOrderDAO orderDAO, IUserDAO userDAO);
    ICategoryDAO createCategoryDAO();
    IProductDAO createProductDAO();
    IShippingDAO createShippingDAO(
            IShippingStatusDAO shippingStatusDAO,
            IOrderDAO orderDAO,
            IAddressDAO addressDAO,
            ICarrierDAO carrierDAO
    );
    IRoleDAO createRoleDAO();

    <T> T createDAO(Class<T> daoInterface);
}