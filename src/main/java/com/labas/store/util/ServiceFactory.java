package com.labas.store.util;

import com.labas.store.dao.*;
import com.labas.store.dao.jdbc.*;
import com.labas.store.service.*;
import com.labas.store.service.impl.*;

/**
 * Service factory to switch between different implementations or databases.
 */
public class ServiceFactory {
    private static final IUserDAO I_USER_DAO = new JDBCUserDAO();
    private static final IOrderStatusDAO I_ORDER_STATUS_DAO = new JDBCOrderStatusDAO();
    private static final IPaymentMethodDAO I_PAYMENT_METHOD_DAO = new JDBCPaymentMethodDAO();
    private static final ICarrierDAO I_CARRIER_DAO = new JDBCCarrierDAO();
    private static final IAddressDAO I_ADDRESS_DAO = new JDBCAddressDAO(I_USER_DAO);
    private static final IShippingStatusDAO I_SHIPPING_STATUS_DAO = new JDBCShippingStatusDAO();
    private static final IOrderDAO I_ORDER_DAO = new JDBCOrderDAO(I_ORDER_STATUS_DAO, I_USER_DAO);
    private static final IPaymentDAO I_PAYMENT_DAO = new JDBCPaymentDAO(I_PAYMENT_METHOD_DAO, I_ORDER_DAO, I_USER_DAO);
    private static final ICategoryDAO I_CATEGORY_DAO = new JDBCCategoryDAO();

    private static final IProductDAO I_PRODUCT_DAO = new JDBCProductDAO();
    private static final IShippingDAO I_SHIPPING_DAO = new JDBCShippingDAO(
            I_SHIPPING_STATUS_DAO,
            I_ORDER_DAO,
            I_ADDRESS_DAO,
            I_CARRIER_DAO
    );
    private static final IRoleDAO I_ROLE_DAO = new JDBCRoleDAO();

    // Service Instances
    private static final IOrderService I_ORDER_SERVICE = new OrderServiceImpl(I_ORDER_DAO);
    private static final IProductService I_PRODUCT_SERVICE = new ProductServiceImpl(I_PRODUCT_DAO);
    private static final IUserService I_USER_SERVICE = new UserServiceImpl(I_USER_DAO);
    private static final ICategoryService I_CATEGORY_SERVICE = new CategoryServiceImpl(I_CATEGORY_DAO);
    private static final IPaymentService I_PAYMENT_SERVICE = new PaymentServiceImpl(I_PAYMENT_DAO);
    private static final IShippingService I_SHIPPING_SERVICE = new ShippingServiceImpl(I_SHIPPING_DAO);
    private static final IRoleService I_ROLE_SERVICE = new RoleServiceImpl(I_ROLE_DAO);
    private static final IOrderStatusService I_ORDER_STATUS_SERVICE = new OrderStatusServiceImpl(I_ORDER_STATUS_DAO);

    public static IOrderService getOrderService() {
        return I_ORDER_SERVICE;
    }

    public static IOrderStatusService getOrderStatusService() {
        return I_ORDER_STATUS_SERVICE;
    }

    public static IProductService getProductService() {
        return I_PRODUCT_SERVICE;
    }

    public static IUserService getUserService() {
        return I_USER_SERVICE;
    }

    public static ICategoryService getCategoryService() {
        return I_CATEGORY_SERVICE;
    }

    public static IPaymentService getPaymentService() {
        return I_PAYMENT_SERVICE;
    }

    public static IShippingService getShippingService() {
        return I_SHIPPING_SERVICE;
    }

    public static IRoleService getRoleService() {
        return I_ROLE_SERVICE;
    }
}
