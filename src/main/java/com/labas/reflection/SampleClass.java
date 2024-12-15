package com.labas.reflection;

public class SampleClass {
    private int id;
    private String name;

    public SampleClass() {
    }

    public SampleClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printInfo() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}