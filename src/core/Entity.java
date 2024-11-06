package core;

/**
 * Base class representing a generic entity with a unique identifier (ID).
 * All other entities in the system will inherit from this class.
 */
public abstract class Entity {
    private long id;

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
