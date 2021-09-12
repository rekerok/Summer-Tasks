package task4.A.Entities;

import Variables.Variables;
import task4.A.DAO.DAO;
import task4.A.DAO.FilmsDAO;
import task4.A.DAO.PeopleDAO;

import java.sql.SQLException;
import java.util.Date;

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
        while (!ageVerification(FilmsDAO.getDateById(this.id_film), PeopleDAO.getDateById(this.id_actor))) {
            this.id_film = Variables.faker.number().numberBetween(DAO.FirstId("films"), DAO.lastId("films"));
            this.id_actor = Variables.faker.number().numberBetween(DAO.FirstId("people"), DAO.lastId("people"));
        }
        ;
    }

    public Film_Actor(int id_film, int id_actor) {
        this(DAO.lastId("film_actor") + 1, id_film, id_actor);
    }

    private boolean ageVerification(Date film, Date actor) {
        return (film.before(actor)) ? true : false;
    }

    @Override
    public String toString() {
        try {
            return "Film_Actor{" +
                    "id = " + getId() +
                    "\n\tid_film = " + FilmsDAO.getTitleById(id_film) +
                    "\n\tid_actor = " + PeopleDAO.getNameById(id_actor) +
                    "\n}";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

}
