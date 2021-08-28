package task4.A.Entities;

import java.util.Date;
import java.util.Objects;

public class Film extends Entity {

    private String title;
    private String country;
    private Date date_release;
    private String description;
    private int producer;

    public Film(int id, String title, String country, Date date_release, String description, int producer) {
        super(id);
        this.title = title;
        this.country = country;
        this.date_release = date_release;
        this.description = description;
        this.producer = producer;
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

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", country='" + country + '\'' +
                ", date_release=" + date_release +
                ", description='" + description + '\'' +
                ", producer=" +  +
                '}';
    }

    public void setDescription(String description) {
        this.description = description;
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
                Objects.equals(producer,film.producer);
    }

}
