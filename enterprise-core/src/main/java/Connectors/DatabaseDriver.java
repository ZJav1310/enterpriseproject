package Connectors;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseDriver {
    Connection getConnection() throws SQLException;
}
