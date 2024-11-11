package core;

import java.util.Objects;

/**
 * Abstract class representing a person with basic attributes like name and email.
 * This class serves as a base for other person-related entities.
 */
public abstract class Person extends Entity {

    private String fullName;
    private String email;

    public Person(long id, String fullName, String email) {
        super(id);
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Person)) return false;
        Person o = (Person) obj;

        return getId() == o.getId() &&
                fullName.equals(o.fullName) &&
                email.equals(o.email);
    }
    public int hashCode() {
        return Objects.hash(
                getId(),
                fullName,
                email
        );
    }

    @Override
    public String toString() {
        return String.format("Information about a person %s email=%s", fullName, email);
    }
}
