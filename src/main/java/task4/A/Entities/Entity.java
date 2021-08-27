package task4.A.Entities;

import java.io.Serializable;

public abstract class Entity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entity(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Entity)) return false;
        Entity entity = (Entity) obj;
        return id == entity.id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
