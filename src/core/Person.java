package core;

/**
 * Abstract class representing a person with basic attributes like name and email.
 * This class serves as a base for other person-related entities.
 */
public abstract class Person extends Entity {

    private String name;
    private String email;

    /**
     * Constructor to initialize the person with id, name, and email.
     *
     * @param id    Unique identifier for the person.
     * @param name  Name of the person.
     * @param email Email address of the person.
     */
    public Person(long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    /**
     * Abstract method for displaying the person's information.
     */
    abstract public String displayInfo();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
