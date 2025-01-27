package com.labas.travelagency;

// Mocked Connection class
public class Connection {
    private final String id;

    public Connection(String id) {
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
