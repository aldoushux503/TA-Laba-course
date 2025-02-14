package com.labas.store.factory.provider;

import com.labas.store.dao.*;
import com.labas.store.factory.IGenericDAOFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DAOProvider {
    private final IGenericDAOFactory daoFactory;
    private final Map<Class<?>, Object> daoCache = new ConcurrentHashMap<>();

    public DAOProvider(IGenericDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @SuppressWarnings("unchecked")
    public <T> T getDAO(Class<T> daoClass) {
        return (T) daoCache.computeIfAbsent(daoClass, this::createDAO);
    }

    private <T> T createDAO(Class<T> daoClass) {
        return switch (daoClass.getSimpleName()) {
            case "IUserDAO" -> (T) daoFactory.createUserDAO();
            case "IOrderStatusDAO" -> (T) daoFactory.createOrderStatusDAO();
            case "IPaymentMethodDAO" -> (T) daoFactory.createPaymentMethodDAO();
            case "ICarrierDAO" -> (T) daoFactory.createCarrierDAO();
            case "IShippingStatusDAO" -> (T) daoFactory.createShippingStatusDAO();
            case "ICategoryDAO" -> (T) daoFactory.createCategoryDAO();
            case "IProductDAO" -> (T) daoFactory.createProductDAO();
            case "IRoleDAO" -> (T) daoFactory.createRoleDAO();
            case "IAddressDAO" -> (T) daoFactory.createAddressDAO(getDAO(IUserDAO.class));
            case "IOrderDAO" -> (T) daoFactory.createOrderDAO(getDAO(IOrderStatusDAO.class), getDAO(IUserDAO.class));
            case "IPaymentDAO" -> (T) daoFactory.createPaymentDAO(
                    getDAO(IPaymentMethodDAO.class),
                    getDAO(IOrderDAO.class),
                    getDAO(IUserDAO.class)
            );
            case "IShippingDAO" -> (T) daoFactory.createShippingDAO(
                    getDAO(IShippingStatusDAO.class),
                    getDAO(IOrderDAO.class),
                    getDAO(IAddressDAO.class),
                    getDAO(ICarrierDAO.class)
            );
            default -> throw new IllegalArgumentException("Unsupported DAO class: " + daoClass);
        };
    }
}
