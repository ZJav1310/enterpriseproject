package Models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

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
//        return "Film [id=" + id + ", title=" + title + ", year=" + year + ", director=" + director + ", stars=" + stars
//                + ", review=" + review + "]";
        return "#" + id + "|" + title + "|" + year + "|" + director + "|" + stars
                + "|" + review;
    }

    // id|title|year|director|stars|reviews
    public Film(List<String> stringList){
        this(Integer.parseInt(stringList.get(0)), stringList.get(1), Integer.parseInt(stringList.get(2)), stringList.get(3),stringList.get(4), stringList.get(5));
    }

}

