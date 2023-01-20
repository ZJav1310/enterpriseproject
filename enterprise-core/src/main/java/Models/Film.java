package Models;

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
        return "#" + id + "|" + title + "|" + year + "|" + director + "|" + stars
                + "|" + review;
    }

    // id|title|year|director|stars|reviews
    public Film(List<String> stringList) {
        this(Integer.parseInt(stringList.get(0)), stringList.get(1), Integer.parseInt(stringList.get(2)), stringList.get(3), stringList.get(4), stringList.get(5));
    }

}

