package task4.A.Entities;

import Variables.Variables;
import task4.A.DAO.Connector;
import task4.A.DAO.DAO;
import task4.A.DAO.FilmsDAO;
import task4.A.DAO.PeopleDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class Film extends Entity {

    private String title;
    private String country;
    private java.util.Date date_release;
    private String description;
    private int producer;

    public Film(int id, String title, String country, Date date_release, String description, int producer) throws SQLException {
        super(id);
        this.title = title;
        this.country = country;
        this.date_release = date_release;
        this.description = description;
        this.producer = producer;
    }

    public Film(String title, String country, Date date_release, String description, int producer) throws SQLException {
        super(DAO.lastId("films") + 1);
        this.title = title;
        this.country = country;
        this.date_release = date_release;
        this.description = description;
        this.producer = producer;
    }

    public Film() {
        super(0);
    }


    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate_release() {
        return date_release;
    }

    public void setDate_release(Date date_release) {
        this.date_release = date_release;
    }

    public String getDescription() {
        return description;
    }


    public static Entity randomEntity() throws SQLException {
        return new Film(Variables.faker.book().title(), Variables.faker.country().name(), Variables.faker.date().birthday(1, 100), Variables.faker.lorem().paragraph(), Variables.faker.number().numberBetween(1, DAO.lastId("people")));
    }

    @Override
    public String toString() {
        return "Human { " +
                "\n\tid = " + +getId() +
                "\n\tname = " + this.title +
                "\n\tcountry = " + this.country +
                "\n\tdate release = " + this.date_release +
                "\n\tdescription = " + this.description +
                "\n\tproducer = " + PeopleDAO.getNameById(producer) +
                "\n}";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducerName() {
        return PeopleDAO.getNameById(producer);
    }

    public int getProducer() {
        return producer;
    }

    public void setProducer(int producer) {
        this.producer = producer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Film film = (Film) obj;
        return Objects.equals(title, film.title) &&
                Objects.equals(country, film.country) &&
                Objects.equals(date_release, film.date_release) &&
                Objects.equals(description, film.description) &&
                Objects.equals(producer, film.producer);
    }

}
