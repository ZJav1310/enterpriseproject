package Builders;

import Connectors.DataSourceConnector;
import Connectors.JDBCConnector;
import Models.DataSourceProp;
import Models.ServerConfig;
import Utils.JSONReader;
import Utils.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DatabaseBuilder {
    private DataSourceConnector dataSourceConnector;
    private DataSourceProp dataSourceProp;

    public DatabaseBuilder() throws ParserConfigurationException {
        setDataSourceProperties();
        setDataSourceConnector();
    }

    // TODO this can be changed so It's not hard coded. -> Reflection : Plugin based Architecture

    /**
     * Method reads database properties given to assign the correct class/driver.
     * Currently, it is hard coded to just take local and mudfoot,
     * However the intention next is for it to read the "driver" field,
     * and then assign the correct class.
     */
    private void setDataSourceConnector() {
        if (Objects.equals(dataSourceProp.getName(), "Local")) {
            this.dataSourceConnector = new JDBCConnector(dataSourceProp);
        }
        if (Objects.equals(dataSourceProp.getName(), "Mudfoot")) {
            this.dataSourceConnector = new JDBCConnector(dataSourceProp);
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
    private void setDataSourceProperties(){
        // parse the JSON file
        try {
//            String DATASOURCEPROPJSON = System.getProperty("user.home") + "\\IdeaProjects\\enterpriseproject\\enterprise-core\\src\\main\\java\\Configuration\\DataSources.JSON";
            String DATASOURCEPROPJSON = System.getProperty("user.home") + "\\ZJ-ENTERPRISE-CONFIG\\DataSources.JSON";
            DataSourceProp[] dataSourcePropList = JSONReader.getInstance().deserialiseObject(Files.readString(Paths.get(DATASOURCEPROPJSON)), DataSourceProp[].class);

            for (DataSourceProp dataSourceProp : dataSourcePropList) {
                System.out.println(dataSourceProp.getName());

                if (Objects.equals(dataSourceProp.getName(), getServerConfig().getName())) {
                    this.dataSourceProp = dataSourceProp;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DataSourceConnector getDataSourceConnector() {
        return dataSourceConnector;
    }

    /**
     * Reads the ServerConfig.XML file,
     * Maps data from the server config file to the ServerConfig class.
     *
     * @return Server Config object
     */
    private ServerConfig getServerConfig() {
        try {
//            String SERVERCONFIGXML = "C:\\Users\\ZTedd\\IdeaProjects\\enterpriseproject\\enterprise-core\\src\\main\\java\\Configuration\\ServerConfig.xml";
            String SERVERCONFIGXML = System.getProperty("user.home") + "\\ZJ-ENTERPRISE-CONFIG\\ServerConfig.xml";
            return XMLReader.getInstance().deserialiseObject(Files.readString(Paths.get(SERVERCONFIGXML)), ServerConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

