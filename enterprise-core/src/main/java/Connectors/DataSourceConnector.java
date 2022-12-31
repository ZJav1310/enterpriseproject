package Connectors;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSourceConnector {
    Connection getConnection() throws SQLException;
}
