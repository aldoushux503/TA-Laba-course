package com.labas.store.util;

import com.labas.store.dao.*;
import com.labas.store.service.*;

/**
 * Service factory to switch between different implementations or databases.
 */
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static final String DATABASE_TYPE = "mysql";
    private static IDAOFactory daoFactory;

    static {
        daoFactory = switch (DATABASE_TYPE.toLowerCase()) {
            case "mysql" -> new MySQLFactory();
            // case "mongodb" -> new MongoDAOFactory();
            default -> throw new UnsupportedOperationException("Unsupported database type: " + DATABASE_TYPE);
        };
    }

    private static final Map<Class<?>, Object> serviceCache = new HashMap<>();
    private static final Map<Class<?>, Object> daoCache = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T getService(Class<T> serviceClass) {
        return (T) serviceCache.computeIfAbsent(serviceClass, ServiceFactory::createService);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getDAO(Class<T> daoClass) {
        return (T) daoCache.computeIfAbsent(daoClass, ServiceFactory::createDAO);
    }

    private static <T> T createDAO(Class<T> daoClass) {
        try {
            Constructor<T> constructor = daoClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating DAO instance: " + daoClass.getSimpleName(), e);
        }
    }

    private static <T> T createService(Class<T> serviceClass) {
        try {
            Constructor<?>[] constructors = serviceClass.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] paramTypes = constructor.getParameterTypes();
                Object[] params = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    params[i] = getDAO(paramTypes[i]);
                }
                return (T) constructor.newInstance(params);
            }
            throw new RuntimeException("No suitable constructor found for: " + serviceClass.getSimpleName());
        } catch (Exception e) {
            throw new RuntimeException("Error creating service instance: " + serviceClass.getSimpleName(), e);
        }
    }
}
