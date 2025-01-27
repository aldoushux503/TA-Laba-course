package com.labas.store.model.dto;

/**
 * DTO for Carrier.
 */
public class CarrierDTO {
    private Long carrierId;
    private String name;
    private String contactNumber;
    private String email;
    private String website;

    public CarrierDTO() {
    }

    public CarrierDTO(Long carrierId, String name, String contactNumber, String email, String website) {
        this.carrierId = carrierId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.website = website;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
