package com.labas.store.model.entity;

/**
 * Represents an address associated with a user or shipping.
 */
public class Address {
    private Long addressId;
    private String city;
    private String street;
    private String zipCode;
    private User user;

    public Address() {
    }

    public Address(Long addressId, String city, String street, String zipCode, User user) {
        this.addressId = addressId;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.user = user;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
