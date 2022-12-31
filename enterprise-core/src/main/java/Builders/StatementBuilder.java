package Builders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class StatementBuilder {

    private final Connection c; // TODO
    private final String statement;
    private final Collection<String> parameters;
    private PreparedStatement p;

    public StatementBuilder(String statement, Collection<String> parameters, Connection c) {
        System.out.println("Statement Builder Created.");
        this.c = c;
        this.statement = statement;
        this.parameters = parameters;
    }

    public StatementBuilder(String statement, Connection c) {
        this(statement, new ArrayList<>(), c);
    }

    public PreparedStatement prepareStatement() {

        int i = 1;

        try {
            p = c.prepareStatement(this.statement);

            if (!this.parameters.isEmpty()) {
                for (String item : this.parameters) {
                    p.setString(i, item);
                    i++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

}

