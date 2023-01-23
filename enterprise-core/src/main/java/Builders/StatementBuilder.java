package Builders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class StatementBuilder {

    private final Connection c;
    private final String statement;
    private final Collection<Object> parameters;
    private PreparedStatement p;

    /**
     * Constructor is used for statements that take in parameters, such as update and insert,
     * The second constructor is for statements like delete and select.
     * Creates an object ready to be passed for preparedStatement.
     * @param statement
     * @param parameters
     * @param c
     */
    public StatementBuilder(String statement, Collection<Object> parameters, Connection c) {
        System.out.println("Statement Builder Created.");
        this.c = c;
        this.statement = statement;
        this.parameters = parameters;
    }

    public StatementBuilder(String statement, Connection c) {
        this(statement, new ArrayList<>(), c);
    }

    /**
     * @return p which is a statement that has been prepared and is ready for execute or update.
     */
    public PreparedStatement prepareStatement() {
        int i = 1;
        try {
            p = c.prepareStatement(this.statement);
            if (!this.parameters.isEmpty()) {
                for (Object item : this.parameters) {
                    p.setObject(i++, item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

}

