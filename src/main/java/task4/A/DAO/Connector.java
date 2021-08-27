package task4.A.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/videoLibrary", "postgres", "qwerty123");
    }
}
