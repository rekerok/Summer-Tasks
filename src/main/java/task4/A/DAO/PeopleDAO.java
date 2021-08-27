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
    public List<Human> getAll() throws SQLException {
        return resultFilmsToList(connection.prepareStatement("SELECT * FROM people").executeQuery());
    }

    @Override
    public Human getEntityById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM people WHERE id = " + id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultFilmsToList(resultSet).get(0);
    }

    @Override
    public void update(Human human) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLquery.UPDATE.QUERY)) {
            preparedStatement.setString(1, human.getFullName());
            preparedStatement.setString(2, human.getCountry());
            preparedStatement.setDate(3, human.getDate_birth());
            preparedStatement.setString(4, human.getMail());
            preparedStatement.setString(5, human.getPhone());
            preparedStatement.setInt(6, human.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void create(Human entity) {
    }

    private List<Human> resultFilmsToList(ResultSet resultSet) throws SQLException {
        ArrayList<Human> listHuman = new ArrayList<>();
        while (resultSet.next()) {
            listHuman.add(new Human(resultSet.getInt("id"), resultSet.getString("full_name"), resultSet.getString("country"), resultSet.getDate("date_birth"), resultSet.getString("mail"), resultSet.getString("phone")));
        }
        return listHuman;
    }

    private enum SQLquery {
        CREATE("INSERT INFO people (id, full_name, country, date_birth, mail, phone) VALUES (DEFAULT, (?),(?),(?),(?))"),
        READ("SELECT * FROM people"),
        UPDATE("UPDATE people SET full_name = (?), country = (?), date_birth = (?), mail = (?), phone = (?) WHERE id = (?)");
        String QUERY;

        SQLquery(String query) {
            this.QUERY = query;
        }
    }
}
