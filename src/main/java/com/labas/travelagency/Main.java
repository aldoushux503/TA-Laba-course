package com.labas.travelagency;


import com.labas.travelagency.exceptions.CustomerNotFoundException;
import com.labas.travelagency.core.Tour;
import com.labas.travelagency.core.Transport;
import com.labas.travelagency.enums.general.Rating;
import com.labas.travelagency.enums.general.TravelPurpose;
import com.labas.travelagency.enums.hotel.MealPlan;
import com.labas.travelagency.enums.hotel.RoomType;
import com.labas.travelagency.enums.transport.TransportationMode;
import com.labas.travelagency.model.agency.Booking;
import com.labas.travelagency.model.agency.Customer;
import com.labas.travelagency.model.agency.TravelAgency;
import com.labas.travelagency.model.hotel.Hotel;
import com.labas.travelagency.model.hotel.Room;
import com.labas.travelagency.model.tour.AdventureTour;
import com.labas.travelagency.model.tour.Attraction;
import com.labas.travelagency.model.transport.Flight;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class Main {

    private static final Logger logger = Logger.getLogger(String.valueOf(Main.class));
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // HelloWorld
        String info = args.length > 0 ? String.join(" ", Arrays.asList(args)) : "World";
        System.out.printf("Hello %s%n", info);

        // QuickSort
        int size = 20;

        int[] randomArray = generateRandomArray(size, 0, 100);

        System.out.println("Before sorting: " + Arrays.toString(randomArray));
        quickSort(randomArray, 0, size - 1);
        System.out.println("After sorting: " + Arrays.toString(randomArray));

        // travel agency
        Customer customer = new Customer(0, "John Doe", "john.doe@example.com", 1000);



        Hotel hotel = new Hotel(0, "Letoh", "Nepal", Rating.EXCELLENT, MealPlan.BREAKFAST);
        Room room = new Room(0, 300, "10A", RoomType.SINGLE, true);
        Room room1 = new Room(1, 300, "101C", RoomType.DOUBLE, true);
        hotel.setRooms(
                List.of(room,
                        room1)
        );

        Attraction attraction = new Attraction(
                0,
                100,
                "Bungee jumping",
                "Kushma"
        );

        Transport transport = new Flight(
                0,
                100,
                "Boeing123",
                "Good airplane",
                "A12",
                true,
                TransportationMode.BUSINESS,
                Rating.EXCELLENT,
                "LOT"
        );

        Tour adventureTour = new AdventureTour(
                0,
                "Mountain Hike",
                "Exciting mountain adventure",
                TravelPurpose.EDUCATION,
                "Hard",
                List.of("Tent", "Knife", "Water"),
                true
        );


        adventureTour.addRoom(room);
        adventureTour.addAttraction(attraction);
        adventureTour.addTransport(transport);


        Booking booking = new Booking(0, customer, adventureTour, LocalDate.now());
        customer.addBooking(booking);
        booking.processBooking();

        TravelAgency agency = new TravelAgency(0, "Sunny Travel", "1234 Sunshine St.");
        agency.addCustomer(customer);
        agency.addTour(adventureTour);

        try {
            logger.info(String.valueOf(agency.findCustomerById(0)));
        } catch (CustomerNotFoundException e) {
            logger.info("Customer not found: " + e.getMessage());
        }

        logger.info(String.valueOf(agency.findTourByName("Mountain Hike")));
        logger.info(String.valueOf(agency.findEmployeesByRole("Manager")));
        logger.info(String.valueOf(agency.removeToursByCondition(c -> c.getName().length() > 2)));
        logger.info(String.format("After remove - %s%n",  agency.getTours()));

        logger.info(String.format(String.valueOf(adventureTour.filterTransportsByRating(3))));
        logger.info(String.format(String.valueOf(adventureTour.getMostPopularTransport())));

        // linkedlist

        configureFileLogging("application.log");

        CustomLinkedList<String> list = new CustomLinkedList<>();

        try {
            List<String> lines = readLinesFromResource("src/main/resources/data.txt");
            if (lines != null) {
                lines.forEach(list::add);
            }
        } catch (IOException e) {
            logger.info("An error occurred while reading lines from resource");
        }

        list.add(1, "Dave");
        logListInfo(list, "After adding 'Dave' at index 1");

        list.remove(2);
        logListInfo(list, "After removing element at index 2");

        list.clear();
        logListInfo(list, "After clearing the list");

        // uniqewords
        String resultPath = "src/main/resources/result.txt";
        String sourcePath = "src/main/resources/source.txt";
        File source = new File(sourcePath);
        File result = new File(resultPath);

        firstSolution(source, result);

        // lambda

        List<Customer> customers = List.of(
                new Customer(1, "Alice", "alice@mail.com", 15000.0),
                new Customer(2, "Bob", "bob@mail.com", 5000.0),
                new Customer(3, "Charlie", "charlie@mail.com", 20000.0),
                new Customer(4, "David", "david@mail.com", 100.0)
        );

        // Example using Extractor: Extract the ID of a customer
        Extractor<Customer> idExtractor = cu -> (int) cu.getId();
        List<Integer> customerIds = customers.stream()
                .map(idExtractor::extract)
                .toList();
        System.out.println("Customer IDs: " + customerIds);

        // Example using Condition: Check if the customer has a balance > 10000
        Condition<Customer> highBalanceCondition = cu -> cu.getBalance() > 10000;
        List<Customer> highBalanceCustomers = customers.stream()
                .filter(highBalanceCondition::test)
                .toList();
        System.out.println("High balance customers:");
        highBalanceCustomers.forEach(c -> System.out.println(c.getFullName()));

        // Example using Transformer: Transform the customer to a simple description string
        Transformer<Customer, String> customerToDescription = cu ->
                "Customer[" + cu.getFullName() + ", Email: " + cu.getEmail() + "]";
        List<String> customerDescriptions = customers.stream()
                .map(customerToDescription::apply)
                .toList();
        System.out.println("Customer descriptions:");
        customerDescriptions.forEach(System.out::println);

        // Using the existing functional interfaces and standard library examples
        // 1. Predicate: Filter customers with a balance > 1000
        Predicate<Customer> balancePredicate = cu -> cu.getBalance() > 1000;
        List<Customer> richCustomers = customers.stream()
                .filter(balancePredicate)
                .toList();

        // 2. Function: Transform customer names to uppercase
        Function<Customer, Customer> toUppercaseName = cu ->
                new Customer(cu.getId(), cu.getFullName().toUpperCase(),
                        cu.getEmail(), cu.getBalance(), cu.getBookings());

        List<Customer> uppercaseNameCustomers = richCustomers.stream()
                .map(toUppercaseName)
                .toList();

        // 3. ToDoubleFunction: Extract the balance of customers
        ToDoubleFunction<Customer> balanceExtractor = Customer::getBalance;
        double totalBalance = customers.stream()
                .mapToDouble(balanceExtractor)
                .sum();

        // 4. Function: Group customers by the first letter of their name
        Function<Customer, Character> groupByFirstLetter = cu -> cu.getFullName().charAt(0);
        Map<Character, List<Customer>> customersByLetter = customers.stream()
                .collect(Collectors.groupingBy(groupByFirstLetter));

        // 5. Consumer: Print customer information
        Consumer<Customer> printCustomer = cu -> System.out.println(
                cu.getFullName() + " - Balance: " + cu.getBalance());

        // Output Results
        System.out.println("\nCustomers with a balance > 1000 (names in uppercase):");
        uppercaseNameCustomers.forEach(printCustomer);

        System.out.println("\nGrouping customers by the first letter of their name:");
        customersByLetter.forEach((letter, group) -> {
            System.out.println(letter + ": " + group);
        });

        System.out.println("\nTotal balance of all customers: " + totalBalance);


        //reflection
        try {
            // 1: Load the Customer class
            Class<?> clazz = Class.forName("com.labas.travelagency.model.agency.Customer");

            // 2: Extract information about fields
            System.out.println("Fields:");
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true); // Allow access to private fields
                System.out.printf("  Name: %s, Type: %s, Modifiers: %s%n",
                        field.getName(), field.getType(), Modifier.toString(field.getModifiers()));
            }

            // 3: Extract information about constructors
            System.out.println("\nConstructors:");
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                System.out.printf("  Constructor: %s, Parameters: %s%n",
                        constructor.getName(), parameterTypes(constructor.getParameterTypes()));
            }

            // 4: Extract information about methods
            System.out.println("\nMethods:");
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.printf("  Name: %s, Return Type: %s, Parameters: %s, Modifiers: %s%n",
                        method.getName(), method.getReturnType(),
                        parameterTypes(method.getParameterTypes()), Modifier.toString(method.getModifiers()));
            }

            // 5: Dynamically create a Customer object using a constructor
            Constructor<?> constructor = clazz.getConstructor(long.class, String.class, String.class, double.class);
            Object c = constructor.newInstance(1L, "Alice", "alice@mail.com", 1000.0);

            // 6: Modify a private field dynamically
            Field balanceField = clazz.getDeclaredField("balance");
            balanceField.setAccessible(true);
            balanceField.set(c, 5000.0); // Update balance to 5000.0

            // 7: Dynamically call the getBalance method
            Method getBalanceMethod = clazz.getMethod("getBalance");
            double balance = (double) getBalanceMethod.invoke(c);
            System.out.println("\nUpdated Balance: " + balance);

            // 8: Dynamically call the toString method
            Method toStringMethod = clazz.getMethod("toString");
            System.out.println("Customer Details: " + toStringMethod.invoke(c));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // threads
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

    private static int[] generateRandomArray(int size, int min, int max) {
        return ThreadLocalRandom.current()
                .ints(size, min, max)
                .toArray();
    }

    // QuickSort algorithm implementation
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Partitions the array and returns the pivot index
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    // Swaps two elements in the array
    private static void swap(int[] array, int i, int j) {
        if (i != j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }


    private static void firstSolution(File source, File result) {
        try {
            String content = FileUtils.readFileToString(source, "UTF-8").toLowerCase();

            Set<String> uniqueWords = new HashSet<>(
                    List.of(
                            StringUtils.split(
                                    content, " \n\t\r\f.,!?;:"
                            )
                    )
            );

            FileUtils.writeStringToFile(
                    result,
                    String.format("The number of unique words - %d", uniqueWords.size()), "UTF-8"
            );

        } catch (IOException e) {
            logger.info("Error occurred while processing the file: {}" + e.getMessage());
        }
    }

    private static String parameterTypes(Class<?>[] parameterTypes) {
        StringBuilder sb = new StringBuilder();
        for (Class<?> type : parameterTypes) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(type.getSimpleName());
        }
        return sb.toString();
    }

    public static List<String> readLinesFromResource(String resourcePath) throws IOException {
        Path path = Paths.get(resourcePath);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to read file: " + resourcePath, e);
            throw e;
        }
    }

    private static void logListInfo(CustomLinkedList<String> list, String message) {
        logger.info(message);
        logger.info("List size: " + list.size());
        logger.info("Is list empty: " + list.isEmpty());
        for (int i = 0; i < list.size(); i++) {
            logger.info("Element at index " + i + ": " + list.get(i));
        }
    }

    private static void configureFileLogging(String logFileName) {
        try {
            Path logDir = Paths.get("target", "logs");

            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
            }

            Path logFile = logDir.resolve(logFileName);

            FileHandler fileHandler = new FileHandler(logFile.toString(), true);

            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            System.err.println("Failed to configure file logging: " + e.getMessage());
        }
    }
}