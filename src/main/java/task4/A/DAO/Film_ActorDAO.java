package task4.A.DAO;

import task4.A.Entities.Entity;
import task4.A.Entities.Film_Actor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Film_ActorDAO extends DAO {
    private DAO filmsDAO = new FilmsDAO(connection);
    private DAO peopleDAO = new PeopleDAO(connection);

    public Film_ActorDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List getAll() throws SQLException {
        return resultList(connection.prepareStatement(SQLqueries.READ.QUERY).executeQuery());
    }

    @Override
    public Entity getEntityById(int id) throws SQLException {
        return null;
    }

    @Override
    public void update(Entity entity) {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void create(Entity entity) {

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
        CREATE("INSERT INTO people (id_film, id_actor) VALUES ((?),(?))"),
        READ("SELECT * FROM film_actor"),
        UPDATE("UPDATE film_actor SET id_film = (?), id_actor WHERE id_film = (?) AND id_actor = (?)"),
        DELETE("DELETE FROM film_actor WHERE WHERE id_film = (?) AND id_actor = (?)");
        String QUERY;

        SQLqueries(String query) {
            this.QUERY = query;
        }
    }
}
