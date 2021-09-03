package task4.A;


import task4.A.DAO.*;
import task4.A.DAO.FilmsDAO;
import task4.A.Entities.Entity;
import task4.A.Entities.Film;
import task4.A.Entities.Human;

import java.sql.*;
import java.util.stream.Stream;

public class Task4A {
    public static void main(String[] args) throws SQLException {
        Connection connection = Connector.connection();
        System.out.println(FilmsDAO.howManyRow());
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
}
