package com.labas.store.model.entities.builder;

import com.labas.store.model.entities.User;

public class UserBuilder {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passwordHash;

    public UserBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder withPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public User build() {
        return new User(userId, firstName, lastName, email, phoneNumber, passwordHash);
    }
}