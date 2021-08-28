package task4.A.DAO;

import task4.A.Entities.Human;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeopleDAO extends DAO<Human> {

    public PeopleDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Human human) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.CREATE.QUERY)) {
            completion(preparedStatement, human);
            int i = preparedStatement.executeUpdate();
            human.setId(howManyRow());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Human human) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.UPDATE.QUERY)) {
            completion(preparedStatement, human);
            preparedStatement.setInt(6, human.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Human> getAll() throws SQLException {
        return resultPeopleToList(connection.prepareStatement("SELECT * FROM people").executeQuery());
    }

    @Override
    public Human getEntityById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM people WHERE id = " + id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultPeopleToList(resultSet).get(0);
    }

    public List<Human> getEntityByCountry(String country) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM people WHERE country = (?)")) {
            preparedStatement.setString(1,country);
            return resultPeopleToList(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLqueries.DELETE.QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void completion(PreparedStatement preparedStatement, Human human) throws SQLException {
        preparedStatement.setString(1, human.getFullName());
        preparedStatement.setString(2, human.getCountry());
        preparedStatement.setDate(3, new java.sql.Date(human.getDate_birth().getTime()));
        preparedStatement.setString(4, human.getMail());
        preparedStatement.setString(5, human.getPhone());
    }

    public int howManyRow() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM people")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Human> resultPeopleToList(ResultSet resultSet) throws SQLException {
        ArrayList<Human> listHuman = new ArrayList<>();
        while (resultSet.next()) {
            listHuman.add(new Human(resultSet.getInt("id"), resultSet.getString("full_name"), resultSet.getString("country"), resultSet.getDate("date_birth"), resultSet.getString("mail"), resultSet.getString("phone")));
        }
        return listHuman;
    }

    private enum SQLqueries {
        CREATE("INSERT INTO people (id, full_name, country, date_birth, mail, phone) VALUES (DEFAULT, (?), (?), (?), (?), (?))"),
        READ("SELECT * FROM people"),
        UPDATE("UPDATE people SET full_name = (?), country = (?), date_birth = (?), mail = (?), phone = (?) WHERE id = (?)"),
        DELETE("DELETE FROM people WHERE id = (?)");
        String QUERY;

        SQLqueries(String query) {
            this.QUERY = query;
        }
    }
}
