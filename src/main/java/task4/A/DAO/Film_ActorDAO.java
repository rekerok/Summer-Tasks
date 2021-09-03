package task4.A.DAO;

import task4.A.Entities.Entity;
import task4.A.Entities.Film_Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Film_ActorDAO extends DAO<Film_Actor> {


    public Film_ActorDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Film_Actor> getAll() throws SQLException {
        return resultList(connection.prepareStatement(SQLqueries.READ.QUERY).executeQuery());
    }

    @Override
    public Film_Actor getEntityById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM people WHERE id = " + id);) {
            return resultList(preparedStatement.executeQuery()).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Film_Actor entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.UPDATE.QUERY)) {
            preparedStatement.setInt(0, entity.getId());
            preparedStatement.setInt(1, entity.getId_film());
            preparedStatement.setInt(2, entity.getId_actor());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.DELETE.QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Film_Actor entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.CREATE.QUERY)) {
            preparedStatement.setInt(1, entity.getId_actor());
            preparedStatement.setInt(2, entity.getId_actor());
            preparedStatement.executeUpdate();
            entity.setId(DAO.lastId("film_actor"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Film_Actor> resultList(ResultSet resultSet) {
        List<Film_Actor> film_actorList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                film_actorList.add(new Film_Actor(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
            }
            return film_actorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film_actorList;
    }

    private enum SQLqueries {
        CREATE("INSERT INTO people (id, id_film, id_actor) VALUES (DEFAULT, (?), (?))"),
        READ("SELECT * FROM film_actor"),
        UPDATE("UPDATE film_actor SET id_film = (?), id_actor = (?) WHERE id_film = (?) AND id_actor = (?)"),
        DELETE("DELETE FROM film_actor WHERE id = (?)");
        String QUERY;

        SQLqueries(String query) {
            this.QUERY = query;
        }
    }
}
