package Builders;

import Connectors.DataSourceConnector;
import Connectors.JDBCConnector;
import Models.DataSourceProperties;
import Models.ServerConfig;
import Utils.Reader;
import Utils.XMLReader;
import com.fasterxml.jackson.xml.XmlMapper;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DatabaseBuilder {
    private static DataSourceProperties dataSourceProperties;
    private DataSourceConnector dataSourceConnector;

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
        if (Objects.equals(dataSourceProperties.getName(), "Local")) {
            this.dataSourceConnector = new JDBCConnector(dataSourceProperties);
        }
        if (Objects.equals(dataSourceProperties.getName(), "Mudfoot")) {
            this.dataSourceConnector = new JDBCConnector(dataSourceProperties);
        }
    }

    /**
     * Calls XMLReader to read DataSources.xml,
     * This is to return the datasources that exist in the file,
     * and then to find the database with the name defined in the server config.
     * <p>
     * Once the database has been found, it will assign it to this.dataSourceProperties.
     *
     * @throws ParserConfigurationException
     */

    //Todo: need to figure out how to deal with the way it finds the files.
    private void setDataSourceProperties() throws ParserConfigurationException {
        Reader reader = new XMLReader();
//        String FILENAME = "/src/main/java/Configuration/DataSources.xml";
        String FILENAME = "../Configuration/DataSources.xml";

        String guess = "C:\\Users\\ZTedd\\IdeaProjects\\enterpriseproject\\enterprise-core\\src\\main\\java\\Configuration\\DataSources.xml";
        String ROOTPATH = new File("").getAbsolutePath();
//        NodeList dbNodeList = reader.readFile(ROOTPATH + FILENAME).getElementsByTagName("Database");
        NodeList dbNodeList = reader.readFile(guess).getElementsByTagName("Database");

        for (int i = 0; i < dbNodeList.getLength(); i++) {
            DataSourceProperties ds = new DataSourceProperties(dbNodeList.item(i));
            if (Objects.equals(ds.getName(), getServerConfig().getName())) {
                dataSourceProperties = ds;
            }
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
        ServerConfig sc;
//        String FILENAME = "/src/main/java/Configuration/ServerConfig.xml";
//        String ROOTPATH = new File("").getAbsolutePath();
//        File file = new File(ROOTPATH + FILENAME);
        File file = new File("C:\\Users\\ZTedd\\IdeaProjects\\enterpriseproject\\enterprise-core\\src\\main\\java\\Configuration\\ServerConfig.xml");

        XmlMapper xmlMapper = new XmlMapper();
        try {
            sc = xmlMapper.readValue(file, ServerConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sc;
    }
}

