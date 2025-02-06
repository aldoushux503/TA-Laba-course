package com.labas.store.util;

import com.labas.store.dao.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DAOProvider {
    private final IDAOFactory daoFactory;
    private final Map<Class<?>, Object> daoCache = new ConcurrentHashMap<>();

    public DAOProvider(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @SuppressWarnings("unchecked")
    public <T> T getDAO(Class<T> daoClass) {
        return (T) daoCache.computeIfAbsent(daoClass, this::createDAO);
    }

    private <T> T createDAO(Class<T> daoClass) {
        if (daoClass == IUserDAO.class) {
            return (T) daoFactory.createUserDAO();
        } else if (daoClass == IOrderStatusDAO.class) {
            return (T) daoFactory.createOrderStatusDAO();
        } else if (daoClass == IPaymentMethodDAO.class) {
            return (T) daoFactory.createPaymentMethodDAO();
        } else if (daoClass == ICarrierDAO.class) {
            return (T) daoFactory.createCarrierDAO();
        } else if (daoClass == IShippingStatusDAO.class) {
            return (T) daoFactory.createShippingStatusDAO();
        } else if (daoClass == ICategoryDAO.class) {
            return (T) daoFactory.createCategoryDAO();
        } else if (daoClass == IProductDAO.class) {
            return (T) daoFactory.createProductDAO();
        } else if (daoClass == IRoleDAO.class) {
            return (T) daoFactory.createRoleDAO();
        } else if (daoClass == IAddressDAO.class) {
            return (T) daoFactory.createAddressDAO(getDAO(IUserDAO.class));
        } else if (daoClass == IOrderDAO.class) {
            return (T) daoFactory.createOrderDAO(getDAO(IOrderStatusDAO.class), getDAO(IUserDAO.class));
        } else if (daoClass == IPaymentDAO.class) {
            return (T) daoFactory.createPaymentDAO(
                    getDAO(IPaymentMethodDAO.class),
                    getDAO(IOrderDAO.class),
                    getDAO(IUserDAO.class)
            );
        } else if (daoClass == IShippingDAO.class) {
            return (T) daoFactory.createShippingDAO(
                    getDAO(IShippingStatusDAO.class),
                    getDAO(IOrderDAO.class),
                    getDAO(IAddressDAO.class),
                    getDAO(ICarrierDAO.class)
            );
        }
        throw new IllegalArgumentException("Unknown DAO class: " + daoClass.getSimpleName());
    }
}
