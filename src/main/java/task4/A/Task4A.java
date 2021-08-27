package task4.A;


import Variables.Variables;
import org.apache.commons.lang3.Validate;
import task4.A.DAO.Connector;
import task4.A.DAO.FilmsDAO;
import task4.A.DAO.PeopleDAO;
import task4.A.Entities.Film;
import task4.A.Entities.Human;

import java.sql.*;

public class Task4A {
    public static void main(String[] args) throws SQLException {
        FilmsDAO filmsDAO = new FilmsDAO(Connector.connection());
        PeopleDAO peopleDAO = new PeopleDAO(Connector.connection());
        System.out.println(peopleDAO.getEntityById(1));
        peopleDAO.update(new Human(1, "feg r g gr", "fregergegre", new java.sql.Date(), "fgergewr", "532453245234534253245"));
        System.out.println(peopleDAO.getEntityById(1));
    }
}
