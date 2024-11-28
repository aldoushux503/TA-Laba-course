package com.labas.LinkedList;

/**
 * A custom implementation of a singly linked list with generics.
 * @param <T> the type of elements stored in the list
 */
public class CustomLinkedList<T> {

    // Node class to represent each element in the list
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head; // Head of the linked list
    private Node<T> tail; // Tail of the linked list for O(1) append
    private int size;     // Size of the linked list

    /**
     * Initializes an empty linked list.
     */
    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds an element to the end of the linked list.
     * @param data the element to add
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds an element at a specific index.
     * @param index the position to insert the element
     * @param data the element to insert
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } else {
            Node<T> prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
            if (index == size) {
                tail = newNode;
            }
        }
        size++;
    }

    /**
     * Removes and returns the element at a specific index.
     * @param index the position of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        T removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
            if (size == 1) {
                tail = null;
            }
        } else {
            Node<T> prev = getNode(index - 1);
            removedData = prev.next.data;
            prev.next = prev.next.next;
            if (index == size - 1) {
                tail = prev;
            }
        }
        size--;
        return removedData;
    }

    /**
     * Returns the element at a specific index.
     * @param index the position of the element
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index) {
        return getNode(index).data;
    }

    /**
     * Returns the size of the linked list.
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the linked list is empty.
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the linked list, removing all elements.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Helper method to get the node at a specific index
    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}