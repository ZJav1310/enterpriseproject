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

        try {
            System.out.println("Preparing Select Statement.");

            try (ResultSet resultSet = new StatementBuilder(select, d.getConnection()).prepareStatement().executeQuery()) {

                while (resultSet.next()) {
                    if (resultSet.getString("id").isEmpty()) {
                        break;
                    } else {
                        films.add(new Film(Integer.parseInt(resultSet.getString("id")), resultSet.getString("title"), Integer.parseInt(resultSet.getString("year")), resultSet.getString("director"), resultSet.getString("stars"), resultSet.getString("review")));
                    }
                }
            }
        } catch (SQLException e) {
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
        ResultSet resultSet = null;

        try {
            List<String> parameters = List.of(Integer.toString(id));
            System.out.println("Preparing Select Statement.");

            System.out.println("Finding.");
            resultSet = new StatementBuilder(select, parameters, d.getConnection()).prepareStatement().executeQuery();

            if (resultSet.next()) {
                System.out.println("Found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (resultSet != null) {
            String output = String.format("Select of %d is successful", id);
            System.out.println(output);
            try {
                return new Film(Integer.parseInt(resultSet.getString("id")), resultSet.getString("title"), Integer.parseInt(resultSet.getString("year")), resultSet.getString("director"), resultSet.getString("stars"), resultSet.getString("review"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

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

        try {
            List<String> parameters = Arrays.asList(
                    f.getTitle(),
                    String.valueOf(f.getYear()),
                    f.getDirector(),
                    f.getReview(),
                    f.getStars()
            );

            System.out.println("Preparing Insert Statement.");
            System.out.println("Inserting.");

            rowsAffected = new StatementBuilder(insert, parameters, d.getConnection()).prepareStatement().executeUpdate();

            if (rowsAffected > 0) {
                String output = String.format("Insert of %s is successful", f.getTitle());
                System.out.println(output);
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
        List<String> parameters = new ArrayList<>(
                List.of(f.getTitle(),
                        Integer.toString(f.getYear()),
                        f.getDirector(),
                        f.getStars(),
                        f.getReview(),
                        Integer.toString(f.getId()))
        );

        int result = 0;

        try {
            if (getById(f.getId()) != null) {
                System.out.println("Preparing Update Statement.");
                System.out.println("Executing Update.");

                result = new StatementBuilder(update, parameters, d.getConnection()).prepareStatement().executeUpdate();

                if (result > 0) {
                    System.out.println("Completed Successfully.");
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        List<String> parameters = new ArrayList<>(List.of(Integer.toString(i)));
        int result = 0;

        try {
            if (getById(i) != null) {
                System.out.println("Preparing Delete Statement. Using ID to find.");
                System.out.println("Executing Delete.");

                result = new StatementBuilder(delete, parameters, d.getConnection()).prepareStatement().executeUpdate();

                if (result > 0) {
                    System.out.println("Completed Successfully.");
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int delete(Film f) {
        final String delete = "DELETE FROM films WHERE id=?";
        List<String> parameters = new ArrayList<>(List.of(Integer.toString(f.getId())));
        int result = 0;

        try {
            if (getById(f.getId()) != null) {
                System.out.println("Preparing Delete Statement. Using Film Objects ID.");
                System.out.println("Executing Delete.");

                result = new StatementBuilder(delete, parameters, d.getConnection()).prepareStatement().executeUpdate();

                if (result > 0) {
                    System.out.println("Completed Successfully.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
