package task4.A.DAO;

import task4.A.Entities.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmsDAO extends DAO<Film> {

    public FilmsDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Film film) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.CREATE.QUERY)) {
            completion(preparedStatement, film);
            int i = preparedStatement.executeUpdate();
            film.setId(howManyRow());
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

    public List getEntityByTitle(String title) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM films WHERE title = " + title);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultFilmsToList(resultSet);
    }

    public List<Film> getEntityByCountry(String country) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM films WHERE country = (?)")) {
            preparedStatement.setString(1,country);
            return resultFilmsToList(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public List getEntityByYear(Date date) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM films WHERE country = " + country);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        return resultFilmsToList(resultSet);
//


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

    public int howManyRow() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM films")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Film> resultFilmsToList(ResultSet resultSet) throws SQLException {
        ArrayList<Film> listFilms = new ArrayList<>();
        while (resultSet.next()) {
            listFilms.add(new Film(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("country"), resultSet.getDate("date_release"), resultSet.getString("description"), resultSet.getInt("producer")));
        }
        return listFilms;
    }

    private enum SQLqueries {
        CREATE("INSERT INFO films (id, title, country, date_release, description, producer) VALUES (DEFAULT, (?),(?),(?),(?),(?))"),
        READ("SELECT * FROM films"),
        UPDATE("UPDATE films SET title = (?), country = (?), date_release = (?), description = (?), producer = (?) WHERE id = (?)"),
        DELETE("DELETE FROM films WHERE id = (?)");
        String QUERY;

        SQLqueries(String query) {
            this.QUERY = query;
        }
    }
}

