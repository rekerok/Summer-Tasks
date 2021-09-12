package task4.A.DAO;

import task4.A.Entities.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public abstract class DAO<T extends Entity> {
    protected static Connection connection;

    static {
        try {
            connection = Connector.connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static int lastId(String db) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM " + db)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int FirstId(String db) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT MIN(id) FROM " + db)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public abstract List<T> getAll() throws SQLException;

    public abstract T getEntityById(int id) throws SQLException;

    public abstract void update(T entity);

    public abstract void delete(int id) throws SQLException;

    public abstract void create(T entity);


}
