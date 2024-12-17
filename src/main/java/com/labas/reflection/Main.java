package com.labas.reflection;

import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) {
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
            Object customer = constructor.newInstance(1L, "Alice", "alice@mail.com", 1000.0);

            // 6: Modify a private field dynamically
            Field balanceField = clazz.getDeclaredField("balance");
            balanceField.setAccessible(true);
            balanceField.set(customer, 5000.0); // Update balance to 5000.0

            // 7: Dynamically call the getBalance method
            Method getBalanceMethod = clazz.getMethod("getBalance");
            double balance = (double) getBalanceMethod.invoke(customer);
            System.out.println("\nUpdated Balance: " + balance);

            // 8: Dynamically call the toString method
            Method toStringMethod = clazz.getMethod("toString");
            System.out.println("Customer Details: " + toStringMethod.invoke(customer));

        } catch (Exception e) {
            e.printStackTrace();
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
}
