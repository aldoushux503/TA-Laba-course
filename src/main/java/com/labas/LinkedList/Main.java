package com.labas.LinkedList;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Alice");
        list.add("Bob");
        list.add("Charlie");
        list.add(1, "Dave");

        System.out.println("Size: " + list.size());
        System.out.println("Element at index 1: " + list.get(1));

        list.remove(2);
        System.out.println("After removal, size: " + list.size());

        list.clear();
        System.out.println("List is empty: " + list.isEmpty());
    }
}