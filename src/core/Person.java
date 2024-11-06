package core;

public abstract class Person extends Entity {

    private String name;
    private String email;

    public Person(long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }


}
