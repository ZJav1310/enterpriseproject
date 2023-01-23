package Queries;

import Builders.StatementBuilder;
import Connectors.DataSourceConnector;
import Models.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class FilmQuery implements DatabaseQuery<Film> {
    private final DataSourceConnector d;

    public FilmQuery(DataSourceConnector d) {
        this.d = d;
    }

    /**
     * @return Collection of Film from the database.
     */
    public Collection<Film> getAll() {
        final String select = "SELECT * FROM films";
        Collection<Film> films = new ArrayList<>();
        System.out.println("Preparing Select Statement.");
        try (ResultSet resultSet = new StatementBuilder(select, d.getConnection()).prepareStatement().executeQuery()) {

            while (resultSet.next()) {
                films.add(new Film(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getString("director"),
                        resultSet.getString("stars"),
                        resultSet.getString("review")));
            }
        } catch (SQLException e) {
            System.out.println("Unable to get all films.");
            e.printStackTrace();
        }
        return films;
    }

    /**
     * This method is used to access database to retrieve Film with the corresponding id given.
     *
     * @param id Film id.
     *           is used to create a List of parameters in String format that is then fed into StatementBuilder.
     *           StatementBuilder takes the query string, parameter List and an active connection
     *           to prepare the statement.
     *           ResultSet is given when executeQuery is performed on the prepared statement.
     *           If ResultSet has a valid .next(), this means the Film with the ID has been found.
     * @return Film Object created from ResultSet given or empty Film Object.
     */
    public Film getById(int id) {
        final String select = "SELECT * FROM films WHERE id=?";

        System.out.println("Preparing Select Statement.");
        try (ResultSet resultSet = new StatementBuilder(select, List.of(id), d.getConnection()).prepareStatement().executeQuery()) {
            System.out.println("Finding.");

            if (resultSet.next()) {
                System.out.printf("Select of %d is successful%n", id);

                return new Film(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getString("director"),
                        resultSet.getString("stars"),
                        resultSet.getString("review"));
            }

        } catch (SQLException e) {
            System.out.println("Unable to get film by ID");
            e.printStackTrace();
        }
        // Returns an empty film
        return new Film();
    }

    /**
     * This method is used to insert a film into the Film table (in database).
     *
     * @param f Film object.
     *          <p>
     *          is a Film object where its respective get methods are used to fill a List with parameters.
     *          Query String, List of parameters and connection is given to prepare statement.
     *          executeUpdate method is used to perform the update and return int greater than 0 if the update performed successfully.
     * @return int which represents the number of rows effected.
     */
    public int insert(Film f) {
        final String insert = "INSERT INTO films (Title, Year, Director, Review, Stars) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = 0;

        List<Object> parameters = Arrays.asList(
                f.getTitle(),
                f.getYear(),
                f.getDirector(),
                f.getReview(),
                f.getStars()
        );
        System.out.println("Preparing Insert Statement.");
        try {
            System.out.println("Inserting.");
            rowsAffected = new StatementBuilder(insert, parameters, d.getConnection()).prepareStatement().executeUpdate();

            if (rowsAffected > 0) {
                System.out.printf("Insert of %s is successful%n", f.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    /**
     * Method takes Film object which used to update row in database.
     *
     * @param f is a Film object.
     *          <p>
     *          Populates List of parameters using Get methods from Film.
     *          Using query String, List of parameters and Connection, prepared statement is created.
     *          The method executeUpdate is used, which returns an int which represents the number of rows effected.
     *          If the number of rows is greater than 0, this means the query is executed successfully.
     * @return int which represents the number of rows effected by the query given.
     */
    public int update(Film f) {
        final String update = "UPDATE films SET title=?, year=?, director=?, stars=?, review=? WHERE id=?";
        List<Object> parameters = new ArrayList<>(
                List.of(f.getTitle(),
                        f.getYear(),
                        f.getDirector(),
                        f.getStars(),
                        f.getReview(),
                        f.getId())
        );

        int result = 0;
        if (getById(f.getId()) != null) {
            System.out.println("Preparing Update Statement.");
            try {
                System.out.println("Executing Update.");
                result = new StatementBuilder(update, parameters, d.getConnection()).prepareStatement().executeUpdate();

                if (result > 0) {
                    System.out.println("Completed Successfully.");
                }
            } catch (SQLException e) {
                System.out.println("Unable to update film.");
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * This method is used to delete a row from the Film table where the id matches.
     *
     * @param i represents Film id.
     *          <p>
     *          Populates List of parameters with the id, which is then pass to a StatementBuilder to prepare the statement.
     *          executeUpdate() is performed which returns the number of rows effected.
     *          If the number of rows is greater than 0, this means the query executes successfully.
     * @return int representing the number of rows effected.
     */
    public int delete(int i) {
        final String delete = "DELETE FROM films WHERE id=?";
        int result = 0;

        if (getById(i) != null) {
            System.out.println("Preparing Delete Statement. Using ID to find.");
            try {
                System.out.println("Executing Delete.");
                result = new StatementBuilder(delete, List.of(i), d.getConnection()).prepareStatement().executeUpdate();
                if (result > 0) {
                    System.out.println("Completed Successfully.");
                    return result;
                }
            } catch (SQLException e) {
                System.out.println("Unable to delete film.");
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public int delete(Film f) {
        final String delete = "DELETE FROM films WHERE id=?";

        int result = 0;
        if (getById(f.getId()) != null) {
            System.out.println("Preparing Delete Statement. Using Film Objects ID.");
            try {
                System.out.println("Executing Delete.");
                result = new StatementBuilder(delete, List.of(f.getId()), d.getConnection()).prepareStatement().executeUpdate();

                if (result > 0) {
                    System.out.println("Completed Successfully.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * This method streams result from getAll() into a filter which is then added to a List.
     * <p>
     * Example use:
     * for (Film f : dc.search((o) -> o.getTitle().contains("WILD"))) {
     * System.out.println(f);
     * }
     * Output:
     * All films titles that contains "WILD"
     *
     * @param filter takes in Lambda expression. e.g (o) -> o.getTitle().contains("Something");
     * @return Collection of film that satisfy the filter.
     */
    public Collection<Film> search(Predicate<Film> filter) {
        return getAll().stream().filter(filter).collect(Collectors.toList());
    }
}
