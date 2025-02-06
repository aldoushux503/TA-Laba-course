package com.labas.store.util;

/**
 * Service factory to switch between different implementations or databases.
 */
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;


public class ServiceProvider {
    private final DAOProvider daoProvider;
    private final Map<Class<?>, Object> serviceCache = new HashMap<>();

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
                    params[i] = daoProvider.getDAO(paramTypes[i]); // Получаем DAO через DAOProvider
                }
                return (T) constructor.newInstance(params);
            }
            throw new RuntimeException("No suitable constructor found for: " + serviceClass.getSimpleName());
        } catch (Exception e) {
            throw new RuntimeException("Error creating service instance: " + serviceClass.getSimpleName(), e);
        }
    }
}
