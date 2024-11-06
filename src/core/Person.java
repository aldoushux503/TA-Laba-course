package core;

public abstract class Person extends Entity {

    private String name;
    private String email;

    public Person(long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

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
