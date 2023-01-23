package Factory;

import Connectors.DatabaseDriver;
import Connectors.JDBCDriver;
import Models.DatabaseProp;

/**
 * Simple factory for retrieving the correct driver for a database based on the
 * properties given.
 */
public class DatabaseDriverFactory {
    public static DatabaseDriver create(DatabaseProp d) throws Exception {
        System.out.println("Finding Driver");
        switch(d.getDriver()) {
            case "JDBC": return new JDBCDriver(d);
            default:
                throw new Exception("No Driver Found.");
        }
    }
}
