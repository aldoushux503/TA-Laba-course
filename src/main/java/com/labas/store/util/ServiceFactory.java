package com.labas.store.util;

import com.labas.store.dao.*;
import com.labas.store.dao.jdbc.*;
import com.labas.store.service.*;
import com.labas.store.service.impl.*;

/**
 * Service factory to switch between different implementations or databases.
 */
public class ServiceFactory {
    private static final String DATABASE_TYPE = "mysql";
    private static DAOFactory daoFactory;

    static {
        if ("mysql".equalsIgnoreCase(DATABASE_TYPE)) {
            daoFactory = new JDBCDAOFactory();
//        } else if ("mongodb".equalsIgnoreCase(DATABASE_TYPE)) {
//            daoFactory = new MongoDAOFactory();
        } else {
            throw new UnsupportedOperationException("Unsupported database type: " + DATABASE_TYPE);
        }
    }

    private static final IUserDAO USER_DAO = daoFactory.createUserDAO();
    private static final IOrderStatusDAO ORDER_STATUS_DAO = daoFactory.createOrderStatusDAO();
    private static final IPaymentMethodDAO PAYMENT_METHOD_DAO = daoFactory.createPaymentMethodDAO();
    private static final ICarrierDAO CARRIER_DAO = daoFactory.createCarrierDAO();
    private static final IAddressDAO ADDRESS_DAO = daoFactory.createAddressDAO(USER_DAO);
    private static final IShippingStatusDAO SHIPPING_STATUS_DAO = daoFactory.createShippingStatusDAO();
    private static final IOrderDAO ORDER_DAO = daoFactory.createOrderDAO(ORDER_STATUS_DAO, USER_DAO);
    private static final IPaymentDAO PAYMENT_DAO = daoFactory.createPaymentDAO(PAYMENT_METHOD_DAO, ORDER_DAO, USER_DAO);
    private static final ICategoryDAO CATEGORY_DAO = daoFactory.createCategoryDAO();
    private static final IProductDAO PRODUCT_DAO = daoFactory.createProductDAO();
    private static final IShippingDAO SHIPPING_DAO = daoFactory.createShippingDAO(SHIPPING_STATUS_DAO, ORDER_DAO, ADDRESS_DAO, CARRIER_DAO);
    private static final IRoleDAO ROLE_DAO = daoFactory.createRoleDAO();

    private static final IOrderService ORDER_SERVICE = new OrderServiceImpl(ORDER_DAO);
    private static final IProductService PRODUCT_SERVICE = new ProductServiceImpl(PRODUCT_DAO);
    private static final IUserService USER_SERVICE = new UserServiceImpl(USER_DAO);
    private static final ICategoryService CATEGORY_SERVICE = new CategoryServiceImpl(CATEGORY_DAO);
    private static final IPaymentService PAYMENT_SERVICE = new PaymentServiceImpl(PAYMENT_DAO);
    private static final IShippingService SHIPPING_SERVICE = new ShippingServiceImpl(SHIPPING_DAO);
    private static final IRoleService ROLE_SERVICE = new RoleServiceImpl(ROLE_DAO);
    private static final IOrderStatusService ORDER_STATUS_SERVICE = new OrderStatusServiceImpl(ORDER_STATUS_DAO);

    public static IOrderService getOrderService() {
        return ORDER_SERVICE;
    }

    public static IProductService getProductService() {
        return PRODUCT_SERVICE;
    }

    public static IUserService getUserService() {
        return USER_SERVICE;
    }

    public static ICategoryService getCategoryService() {
        return CATEGORY_SERVICE;
    }

    public static IPaymentService getPaymentService() {
        return PAYMENT_SERVICE;
    }

    public static IShippingService getShippingService() {
        return SHIPPING_SERVICE;
    }

    public static IRoleService getRoleService() {
        return ROLE_SERVICE;
    }

    public static IOrderStatusService getOrderStatusService() {
        return ORDER_STATUS_SERVICE;
    }
}