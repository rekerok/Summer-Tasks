package task4.A.DAO;

import task4.A.Entities.Film;
import task4.A.Entities.Human;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmsDAO extends DAO<Film> {
    public FilmsDAO() throws SQLException {
        super(Connector.connection());
    }

    public FilmsDAO(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void create(Film film) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.CREATE.QUERY)) {
            completion(preparedStatement, film);
            int i;
            i = preparedStatement.executeUpdate();
            film.setId(lastId("films"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getAll() throws SQLException {
        return resultFilmsToList(connection.prepareStatement(SQLqueries.READ.QUERY).executeQuery());
    }

    @Override
    public Film getEntityById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM films WHERE id = " + id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultFilmsToList(resultSet).get(0);
    }

    public static List getEntityByTitle(String title) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM films WHERE title = " + title);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultFilmsToList(resultSet);
    }

    public static List<Film> getEntityByCountry(String country) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM films WHERE country = (?)")) {
            preparedStatement.setString(1, country);
            return resultFilmsToList(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List getFilmByYear(int from, int to) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.SELECT_BETWEEN_DATE.QUERY)) {
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, to);
            return resultFilmsToList(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTitleById(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT title FROM films WHERE id = (?)")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                return resultSet.getString(1);
        }
        return "";
    }



    public static java.util.Date getDateById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT date_release FROM films WHERE id = (?)")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                return resultSet.getDate(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery("DELETE FROM films WHERE id =" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Film film) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.UPDATE.QUERY)) {
            completion(preparedStatement, film);
            preparedStatement.setInt(6, film.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void completion(PreparedStatement preparedStatement, Film film) throws SQLException {
        preparedStatement.setString(1, film.getTitle());
        preparedStatement.setString(2, film.getCountry());
        preparedStatement.setDate(3, new java.sql.Date(film.getDate_release().getTime()));
        preparedStatement.setString(4, film.getDescription());
        preparedStatement.setInt(5, film.getProducer());
    }

    public static int howManyRow() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM films")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static List<Film> resultFilmsToList(ResultSet resultSet) throws SQLException {
        List<Film> listFilms = new ArrayList<>();
        while (resultSet.next()) {
            listFilms.add(new Film(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("country"), resultSet.getDate("date_release"), resultSet.getString("description"), resultSet.getInt("producer")));
        }
        return listFilms;
    }

    private enum SQLqueries {
        CREATE("INSERT INTO films (id, title, country, date_release, description, producer) VALUES (DEFAULT, (?),(?),(?),(?),(?))"),
        READ("SELECT * FROM films"),
        SELECT_BETWEEN_DATE("SELECT * FROM films WHERE EXTRACT(YEAR FROM date_release) BETWEEN (?) AND (?)"),
        UPDATE("UPDATE films SET title = (?), country = (?), date_release = (?), description = (?), producer = (?) WHERE id = (?)"),
        DELETE("DELETE FROM films WHERE id = (?)");
        String QUERY;

        SQLqueries(String query) {
            this.QUERY = query;
        }
    }
}

