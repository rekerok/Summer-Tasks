package task4.A.Entities;

import Variables.Variables;
import task4.A.DAO.Connector;
import task4.A.DAO.DAO;
import task4.A.DAO.PeopleDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

public class Human extends Entity {
    private String fullName;
    private String country;
    private java.util.Date date_birth;
    private String mail;
    private String phone;

    public Human(int id, String fullName, String country, java.util.Date date_birth, String mail, String phone) {
        super(id);
        this.fullName = fullName;
        this.country = country;
        this.date_birth = date_birth;
        this.mail = mail;
        this.phone = phone;
    }

    public Human(String fullName, String country, java.util.Date date_birth, String mail, String phone) throws SQLException {
        super(DAO.lastId("people") + 1);
        this.fullName = fullName;
        this.country = country;
        this.date_birth = date_birth;
        this.mail = mail;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        Human human = (Human) obj;
        return Objects.equals(fullName, human.fullName) &&
                Objects.equals(country, human.country) &&
                Objects.equals(mail, human.mail) &&
                Objects.equals(phone, human.phone);
    }

    public java.util.Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public static Human randomEntity() throws SQLException {
        return new Human(Variables.faker.name().fullName(), Variables.faker.country().name(), Variables.faker.date().birthday(20, 100), Variables.faker.internet().emailAddress(), Variables.faker.phoneNumber().cellPhone());
    }

    @Override
    public String toString() {
        return "Human { " +
                "\n\tid = " + +getId() +
                "\n\tname = " + this.fullName +
                "\n\tcountry = " + this.country +
                "\n\tdate birth = " + this.date_birth +
                "\n\tmail = " + this.mail +
                "\n\tphone = " + this.phone +
                "\n}";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
