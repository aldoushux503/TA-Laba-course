package com.labas.store.util;

import com.labas.store.dao.impl.*;
import com.labas.store.service.*;
import com.labas.store.service.impl.*;

/**
 * Service factory to switch between different implementations or databases.
 */
public class ServiceFactory {
    private static final OrderService orderService = new OrderServiceImpl(new OrderDAOImpl());
    private static final ProductService productService = new ProductServiceImpl(new ProductDAOImpl());
    private static final UserService userService = new UserServiceImpl(new UserDAOImpl());
    private static final CategoryService categoryService = new CategoryServiceImpl(new CategoryDAOImpl());
    private static final PaymentService paymentService = new PaymentServiceImpl(new PaymentDAOImpl());
    private static final ShippingService shippingService = new ShippingServiceImpl(new ShippingDAOImpl());
    private static final RoleService roleService = new RoleServiceImpl(new RoleDAOImpl());

    public static OrderService getOrderService() {
        return orderService;
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
