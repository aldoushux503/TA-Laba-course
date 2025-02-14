package com.labas.store.factory;

import com.labas.store.dao.*;

public interface IGenericDAOFactory {
    public abstract IUserDAO createUserDAO();
    public abstract IOrderStatusDAO createOrderStatusDAO();
    public abstract IPaymentMethodDAO createPaymentMethodDAO();
    public abstract ICarrierDAO createCarrierDAO();
    public abstract IAddressDAO createAddressDAO(IUserDAO userDAO);
    public abstract IShippingStatusDAO createShippingStatusDAO();
    public abstract IOrderDAO createOrderDAO(IOrderStatusDAO orderStatusDAO, IUserDAO userDAO);
    public abstract IPaymentDAO createPaymentDAO(IPaymentMethodDAO paymentMethodDAO, IOrderDAO orderDAO, IUserDAO userDAO);
    public abstract ICategoryDAO createCategoryDAO();
    public abstract IProductDAO createProductDAO();
    public abstract IShippingDAO createShippingDAO(
            IShippingStatusDAO shippingStatusDAO,
            IOrderDAO orderDAO,
            IAddressDAO addressDAO,
            ICarrierDAO carrierDAO
    );
    public abstract IRoleDAO createRoleDAO();
}
