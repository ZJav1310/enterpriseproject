package Controllers;

import Builders.DatabaseBuilder;
import Models.Film;
import Queries.DatabaseQuery;
import Queries.FilmQuery;

import javax.xml.parsers.ParserConfigurationException;
import java.sql.SQLException;
import java.util.Collection;

/**
 * This class purpose is to be the bridge between this back end and whatever interface to exist.
 */
public class Commands {
    private final DatabaseQuery<Film> filmQuery;

    public Commands() throws ParserConfigurationException, SQLException {
        filmQuery = new FilmQuery(new DatabaseBuilder().getDataSourceConnector());
    }

    /**
     * @param title    Film title.
     * @param year     Film year of release.
     * @param director Film director.
     * @param stars    Film star(s).
     * @param review   Film review.
     * @return int Number of rows effected by this command.
     */
    public int insertFilm(String title, int year, String director, String stars, String review) {
        return filmQuery.insert(new Film(title, year, director, stars, review));
    }

    public int insertFilm(Film f) {
        return filmQuery.insert(f);
    }

    /**
     * This method is used to update Film given which is found using its id.
     *
     * @param id       Film id.
     * @param title    Film title.
     * @param year     Film year of release.
     * @param director Film director.
     * @param stars    Film star(s).
     * @param review   Film review.
     * @return int Number of rows effected by this command.
     */
    public int updateFilm(int id, String title, int year, String director, String stars, String review) {
        return filmQuery.update(new Film(id, title, year, director, stars, review));
    }
    public int updateFilm(Film f) {
        return filmQuery.update(f);
    }

    /**
     * This method is used to delete a film with the id given.
     *
     * @param id Film id.
     * @return int Number of rows effected by this command.
     */
    public int deleteFilm(int id) {
        return filmQuery.delete(id);
    }

    /**
     * @return Collection of Films returned by getAll();
     */
    public Collection<Film> getAllFilms() {
        return filmQuery.getAll();
    }

    /**
     * This method returns a Film object where its fields are populated with the values of the found film.
     *
     * @param id Film id.
     * @return Film found to have the id given.
     */
    public Film getById(int id) {
        return filmQuery.getById(id);
    }
}
