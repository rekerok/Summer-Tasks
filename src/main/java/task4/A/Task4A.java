package task4.A;


import Variables.Variables;
import org.apache.commons.lang3.Validate;
import task4.A.DAO.Connector;
import task4.A.DAO.FilmsDAO;
import task4.A.DAO.FilmsDAO;
import task4.A.DAO.PeopleDAO;

import java.sql.*;
import java.util.stream.Stream;

public class Task4A {
    public static void main(String[] args) throws SQLException {
        FilmsDAO filmsDAO = new FilmsDAO(Connector.connection());
        PeopleDAO peopleDAO = new PeopleDAO(Connector.connection());
        System.out.println(filmsDAO.getEntityById(1));
    }
}
