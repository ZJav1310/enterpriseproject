package Connectors;

import Models.DatabaseProp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDriver implements DatabaseDriver {
    private DatabaseProp d;
    private Connection c;

    public JDBCDriver(DatabaseProp d) {
        this.d = d;
    }
    private void initialiseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String url = String.format("jdbc:mysql://%s:%s/%s", d.getUrl(), d.getPort(), d.getSchema());
            this.c = DriverManager.getConnection(url, d.getUser(), d.getPass());
            System.out.println("Connection created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            c.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if (this.c == null || this.c.isClosed()) {
            initialiseConnection();
        }
        return c;
    }

}
