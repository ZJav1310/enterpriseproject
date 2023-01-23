package Builders;

import Connectors.DatabaseDriver;
import Factory.DatabaseDriverFactory;
import Models.DatabaseProp;
import Models.ServerConfig;
import Utils.JSONReader;
import Utils.XMLReader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class DatabaseBuilder {
    private DatabaseDriver databaseDriver;
    private final DatabaseProp databaseProp; // Database Properties

    /**
     * Currently forces user to read database configuration from a file,
     * Initially done to help with prototyping the rest of project,
     * However this is a poor implementation since it does not allow the user to
     * decide if the variables are from File, ENV or elsewhere.
     */
    public DatabaseBuilder(String location){
        this.databaseProp = setDataSourceProperties(location);
        if(this.databaseProp != null) {
            setDatabaseDriver();
        }
    }

    /**
     * Method called DatabaseDriverFactory,
     * passes dataSourcepProp derived from reading file (at the moment)
     */
    private void setDatabaseDriver() {
        try{
            this.databaseDriver = DatabaseDriverFactory.create(databaseProp);
        } catch (Exception e) {
            System.err.println("Error retrieving Driver: " + e);
        }
    }

    /**
     * Calls XMLReader to read DataSources.xml,
     * This is to return the datasources that exist in the file,
     * and then to find the database with the name defined in the server config.
     * <p>
     * Once the database has been found, it will assign it to this.dataSourceProperties.
     */

    // Example: C:\Users\ZTedd\ZJ-ENTERPRISE-CONFIG
    private DatabaseProp setDataSourceProperties(String location) {
        switch(location.toLowerCase()){
            case "file": return this.setFromFile();
            case "env": return this.setFromEnv();
            default:
                System.err.println("Unknown Location.");
        }
        return null;
    }

    private DatabaseProp setFromFile(){
        try {
            ServerConfig sc = this.setServerConfig();

            String DATASOURCEPROPJSON = System.getProperty("user.home") + "\\ZJ-ENTERPRISE-CONFIG\\DataSources.JSON";
            System.out.println("Trying to get file, expected location: " + DATASOURCEPROPJSON);
            DatabaseProp[] databasePropList = JSONReader.getInstance().deserialiseObject(Files.readString(Paths.get(DATASOURCEPROPJSON)), DatabaseProp[].class);

            for (DatabaseProp databaseProp : databasePropList) {
                System.out.println("Reading: " + databaseProp.getName());
                if (Objects.equals(databaseProp.getName(), sc.getName())) {
                    System.out.println("Found requested properties of " + databaseProp.getName() + " from DataSources.json...setting now.");
                    return databaseProp;
                }
            }

        } catch (Exception e ) {
            System.err.println("Database properties could not be found: " + e);
//            throw new RuntimeException(e);
        }
        return null;
    }

    private DatabaseProp setFromEnv(){
        throw new UnsupportedOperationException("ENV is not implemented");
    }

    /**
     * Reads the ServerConfig.XML file,
     * Maps data from the server config file to the ServerConfig class.
     *
     * @return Server Config object
     */

    private ServerConfig setServerConfig() {
        ServerConfig result;
        try {
            String SERVERCONFIGXML = System.getProperty("user.home") + "\\ZJ-ENTERPRISE-CONFIG\\ServerConfig.xml";
            System.out.println("Trying to get file, expected location: " + SERVERCONFIGXML);
            result = XMLReader.getInstance().deserialiseObject(Files.readString(Paths.get(SERVERCONFIGXML)), ServerConfig.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public DatabaseDriver getDataSourceConnector() {
        return databaseDriver;
    }
}





