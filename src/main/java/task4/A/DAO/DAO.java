package task4.A.DAO;

import task4.A.Entities.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO <T extends Entity>{
    protected Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public abstract List<T> getAll() throws SQLException;
    public abstract T getEntityById(int id) throws SQLException;
    public abstract void update(T entity);
    public abstract void delete(int id) throws SQLException;
    public abstract void create(T entity);
}
