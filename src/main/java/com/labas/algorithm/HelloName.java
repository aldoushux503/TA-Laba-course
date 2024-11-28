package com.labas.algorithm;

import java.util.Arrays;

public class HelloName {
    public static void main(String[] args) {
        String info = args.length > 0 ? String.join(" ", Arrays.asList(args)) : "World";
        System.out.printf("Hello %s%n", info);
    }
}