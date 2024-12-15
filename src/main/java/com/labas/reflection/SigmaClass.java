package com.labas.reflection;

public class SigmaClass {
    private int id;
    private String name;

    public SigmaClass() {
    }

    public SigmaClass(int id, String name) {
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