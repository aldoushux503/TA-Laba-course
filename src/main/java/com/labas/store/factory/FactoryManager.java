package com.labas.store.factory;

import com.labas.store.factory.provider.DAOProvider;
import com.labas.store.factory.provider.ServiceProvider;

public class FactoryManager {
    private static final String DATABASE_TYPE = "mybatis";
    private static final DAOProvider daoProvider;
    private static final ServiceProvider serviceProvider;

    static {
        IGenericDAOFactory daoFactory = switch (DATABASE_TYPE.toLowerCase()) {
            case "mysql" -> new MySQLDAOFactory();
            case "mybatis" -> new MyBatisDAOFactory();
            // case "mongodb" -> new MongoDAOFactory();
            default -> throw new UnsupportedOperationException("Unsupported database type: " + DATABASE_TYPE);
        };

        daoProvider = new DAOProvider(daoFactory);
        serviceProvider = new ServiceProvider(daoProvider);
    }

    public static DAOProvider getDAOProvider() {
        return daoProvider;
    }

    public static ServiceProvider getServiceProvider() {
        return serviceProvider;
    }
}
