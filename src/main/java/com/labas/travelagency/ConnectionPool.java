package com.labas.travelagency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Thread-safe, lazy-initialized Connection Pool
class ConnectionPool {
    private static volatile ConnectionPool instance;
    private final BlockingQueue<Connnection> pool;
    private final int size;

    private ConnectionPool(int size) {
        this.size = size;
        this.pool = new LinkedBlockingQueue<>(size);
        for (int i = 1; i <= size; i++) {
            pool.offer(new Connnection("Connection-" + i));
        }
    }

    public static ConnectionPool getInstance(int size) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(size);
                }
            }
        }
        return instance;
    }

    public Connnection acquireConnection() throws InterruptedException {
        return pool.take(); // Blocks if no connection is available
    }

    public void releaseConnection(Connnection connnection) {
        pool.offer(connnection);
    }
}
