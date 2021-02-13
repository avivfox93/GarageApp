package com.example.garageapp;

public class Garage {
    private String[] Cars;
    private boolean open;
    private String address;
    private String name;

    public Garage() {
    }

    public String[] getCars() {
        return Cars;
    }

    public Garage setCars(String[] cars) {
        Cars = cars;
        return this;
    }

    public boolean isOpen() {
        return open;
    }

    public Garage setOpen(boolean open) {
        this.open = open;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Garage setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }

    public Garage setName(String name) {
        this.name = name;
        return this;
    }
}
