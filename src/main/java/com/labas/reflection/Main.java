package com.labas.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) {
        try {
            // 1: Load the class
            Class<?> clazz = Class.forName("SampleClass");

            // 2: Extract information about fields
            System.out.println("Fields:");
            for (Field field : clazz.getDeclaredFields()) {
                System.out.printf("  Name: %s, Type: %s, Modifiers: %s%n",
                        field.getName(), field.getType(), Modifier.toString(field.getModifiers()));
            }

            //  3: Extract information about constructors
            System.out.println("\nConstructors:");
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                System.out.printf("  Constructor: %s, Parameters: %s%n",
                        constructor.getName(), ParameterTypes(constructor.getParameterTypes()));
            }

            // 4: Extract information about methods
            System.out.println("\nMethods:");
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.printf("  Name: %s, Return Type: %s, Parameters: %s, Modifiers: %s%n",
                        method.getName(), method.getReturnType(),
                        ParameterTypes(method.getParameterTypes()), Modifier.toString(method.getModifiers()));
            }

            // 5: Dynamically create an object using a constructor
            Constructor<?> constructor = clazz.getConstructor(int.class, String.class);
            Object obj = constructor.newInstance(101, "John Doe");

            // 6: Call a method dynamically
            Method setNameMethod = clazz.getMethod("setName", String.class);
            setNameMethod.invoke(obj, "Jane Doe"); // Dynamically set name to "Jane Doe"

            Method printInfoMethod = clazz.getMethod("printInfo");
            printInfoMethod.invoke(obj); // Dynamically call printInfo()

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String ParameterTypes(Class<?>[] parameterTypes) {
        StringBuilder sb = new StringBuilder();
        for (Class<?> type : parameterTypes) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(type.getSimpleName());
        }
        return sb.toString();
    }
}
