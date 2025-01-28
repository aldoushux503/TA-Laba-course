package com.labas.store.util;

import com.labas.store.dao.*;
import com.labas.store.dao.impl.*;
import com.labas.store.service.*;
import com.labas.store.service.impl.*;

/**
 * Service factory to switch between different implementations or databases.
 */
public class ServiceFactory {
    private static final UserDAO userDAO = new UserDAOImpl();
    private static final OrderStatusDAO orderStatusDAO = new OrderStatusDAOImpl();
    private static final PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAOImpl();
    private static final CarrierDAO carrierDAO = new CarrierDAOImpl();
    private static final AddressDAO addressDAO = new AddressDAOImpl(userDAO);
    private static final ShippingStatusDAO shippingStatusDAO = new ShippingStatusDAOImpl();
    private static final OrderDAO orderDAO = new OrderDAOImpl(orderStatusDAO, userDAO);
    private static final PaymentDAO paymentDAO = new PaymentDAOImpl(paymentMethodDAO, orderDAO, userDAO);
    private static final CategoryDAO categoryDAO = new CategoryDAOImpl();

    private static final ProductDAO productDAO = new ProductDAOImpl();
    private static final ShippingDAO shippingDAO = new ShippingDAOImpl(
            shippingStatusDAO,
            orderDAO,
            addressDAO,
            carrierDAO
    );
    private static final RoleDAO roleDAO = new RoleDAOImpl();

    // Service Instances
    private static final OrderService orderService = new OrderServiceImpl(orderDAO);
    private static final ProductService productService = new ProductServiceImpl(productDAO);
    private static final UserService userService = new UserServiceImpl(userDAO);
    private static final CategoryService categoryService = new CategoryServiceImpl(categoryDAO);
    private static final PaymentService paymentService = new PaymentServiceImpl(paymentDAO);
    private static final ShippingService shippingService = new ShippingServiceImpl(shippingDAO);
    private static final RoleService roleService = new RoleServiceImpl(roleDAO);
    private static final OrderStatusService orderStatusService = new OrderStatusServiceImpl(orderStatusDAO);

    public static OrderService getOrderService() {
        return orderService;
    }

    public static OrderStatusService getOrderStatusService() {
        return orderStatusService;
    }

    public static ProductService getProductService() {
        return productService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static CategoryService getCategoryService() {
        return categoryService;
    }

    public static PaymentService getPaymentService() {
        return paymentService;
    }

    public static ShippingService getShippingService() {
        return shippingService;
    }

    public static RoleService getRoleService() {
        return roleService;
    }
}
