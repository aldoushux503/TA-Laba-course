package com.labas.store.factory.provider;

/**
 * Service factory to switch between different implementations or databases.
 */
import com.labas.store.service.IOrderService;
import com.labas.store.service.decorator.LoggingOrderServiceDecorator;
import com.labas.store.service.proxy.CachingOrderServiceProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ServiceProvider {
    private final DAOProvider daoProvider;
    private final Map<Class<?>, Object> serviceCache = new ConcurrentHashMap<>();

    public ServiceProvider(DAOProvider daoProvider) {
        this.daoProvider = daoProvider;
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> serviceClass) {
        return (T) serviceCache.computeIfAbsent(serviceClass, this::createService);
    }

    private <T> T createService(Class<T> serviceClass) {
        try {
            for (Constructor<?> constructor : serviceClass.getDeclaredConstructors()) {
                Class<?>[] paramTypes = constructor.getParameterTypes();
                Object[] params = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    params[i] = daoProvider.getDAO(paramTypes[i]);
                }
                return (T) constructor.newInstance(params);
            }
            throw new RuntimeException("No suitable constructor found for: " + serviceClass.getSimpleName());
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error creating service instance: " + serviceClass.getSimpleName(), e);
        }
    }

    public <T> T getDecoratedService(Class<T> serviceClass) {
        T service = getService(serviceClass);
        if (service instanceof IOrderService) {
            return (T) new LoggingOrderServiceDecorator((IOrderService) service);
        }
        return service;
    }

    public <T> T getProxyService(Class<T> serviceClass) {
        T service = getService(serviceClass);
        if (service instanceof IOrderService) {
            return (T) new CachingOrderServiceProxy((IOrderService) service);
        }
        return service;
    }
}
