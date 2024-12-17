package com.labas.threads;

import java.util.concurrent.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Step 2: Threads using Runnable and Thread
        Runnable task1 = () -> System.out.println(Thread.currentThread().getName() + " is running...");
        Thread thread1 = new Thread(task1, "Runnable-Thread-1");
        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " is running..."),
                "Thread-2");

        thread1.start();
        thread2.start();

        // Wait for threads to finish
        thread1.join();
        thread2.join();

        // Step 3: Thread Pool (5 threads acquire connections, 2 wait)
        int poolSize = 5;
        ConnectionPool connectionPool = ConnectionPool.getInstance(poolSize);
        ExecutorService threadPool = Executors.newFixedThreadPool(7);

        Callable<String> connectionTask = () -> {
            Connection connection = connectionPool.acquireConnection();
            System.out.println(Thread.currentThread().getName() + " acquired " + connection);
            Thread.sleep(2000); // Simulate work
            connectionPool.releaseConnection(connection);
            return "Released: " + connection;
        };

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            futures.add(threadPool.submit(connectionTask));
        }

        for (Future<String> future : futures) {
            System.out.println(future.get()); // Wait for each task to complete
        }

        threadPool.shutdown();

        // Step 5: CompletableFuture example
        System.out.println("\n--- CompletableFuture Example ---");
        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                try {
                    Connection connection = connectionPool.acquireConnection();
                    System.out.println(Thread.currentThread().getName() + " (Async) acquired " + connection);
                    Thread.sleep(2000);
                    connectionPool.releaseConnection(connection);
                    System.out.println(Thread.currentThread().getName() + " (Async) released " + connection);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            completableFutures.add(cf);
        }

        // Wait for all CompletableFuture tasks to finish
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();

        System.out.println("\nAll tasks completed.");
    }
}
