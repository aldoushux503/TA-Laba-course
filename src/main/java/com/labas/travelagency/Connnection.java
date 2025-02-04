package com.labas.travelagency;

// Mocked Connection class
public class Connnection {
    private final String id;

    public Connnection(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Connection{" + "id='" + id + '\'' + '}';
    }
}
