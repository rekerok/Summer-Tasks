package task4.A.Entities;

import task4.A.DAO.DAO;

public class Film_Actor extends Entity {
    private int id_film;


    public int id_actor;

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public int getId_actor() {
        return id_actor;
    }

    public void setId_actor(int id_actor) {
        this.id_actor = id_actor;
    }

    public Film_Actor(int id, int id_film, int id_actor) {
        super(id);
        this.id_film = id_film;
        this.id_actor = id_actor;
    }

    public Film_Actor(int id_film, int id_actor) {
        this(DAO.lastId("film_actor") + 1, id_film, id_actor);
    }

    @Override
    public String toString() {
        return "Film_Actor{" +
                "id = " + getId() +
                "id_film=" + id_film +
                ", id_actor=" + id_actor +
                '}';
    }

}
