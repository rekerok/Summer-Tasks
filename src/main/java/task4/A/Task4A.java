package task4.A;


import Variables.Variables;
import task4.A.DAO.*;
import task4.A.DAO.FilmsDAO;
import task4.A.Entities.Entity;
import task4.A.Entities.Film;
import task4.A.Entities.Film_Actor;
import task4.A.Entities.Human;

import java.sql.*;
import java.util.stream.Stream;

public class Task4A {
    public static void main(String[] args) throws SQLException {
        Connection connection = Connector.connection();

        Film_ActorDAO film_actorDAO = new Film_ActorDAO();
//        film_actorDAO.create(new Film_Actor(/*Variables.faker.number().numberBetween(DAO.FirstId("films"), DAO.lastId("films"))*/7, Variables.faker.number().numberBetween(DAO.FirstId("people"), DAO.lastId("people"))));

        System.out.println(film_actorDAO.actorOnFilm(3));

    }

    public static void addFilmToDB(int count, DAO dao) {
        Stream.generate(() -> {
            try {
                return Film.randomEntity();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }).limit(count).forEach(dao::create);
    }

    public static void addActorToDB(int count, DAO dao) {
        Stream.generate(() -> {
            try {
                return Human.randomEntity();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }).limit(count).forEach(dao::create);
    }

    public int numberBetween(int min, int max) {
        return Variables.faker.number().numberBetween(min, max);
    }
}
