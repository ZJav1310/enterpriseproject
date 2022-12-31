package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class Film {
    int id;
    String title;
    int year;
    String director;
    String stars;
    String review;

    public Film(int id, String title, int year, String director, String stars, String review) {
        super();
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.stars = stars;
        this.review = review;
    }

    public Film() {
        super();
    }

    public Film(String title, int year, String director, String stars, String review) {
        this(0, title, year, director, stars, review);
    }

    public Film(ResultSet resultSet) throws SQLException {
        this(Integer.parseInt(resultSet.getString("id")), resultSet.getString("title"), Integer.parseInt(resultSet.getString("year")), resultSet.getString("director"), resultSet.getString("stars"), resultSet.getString("review"));
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getStars() {
        return stars;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Film [id=" + id + ", title=" + title + ", year=" + year + ", director=" + director + ", stars=" + stars
                + ", review=" + review + "]";
    }

    public LinkedHashMap<String, String> toArray() {
        LinkedHashMap<String, String> film = new LinkedHashMap<>();

        film.put("id", String.valueOf(id));
        film.put("title", title);
        film.put("year", String.valueOf(year));
        film.put("director", director);
        film.put("stars", stars);
        film.put("review", review);

        return film;
    }

}

