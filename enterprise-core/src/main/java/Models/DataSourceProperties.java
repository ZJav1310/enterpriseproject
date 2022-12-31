package Models;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DataSourceProperties {
    private final String name;
    private final String url;
    private final String user;
    private final String pass;
    private final String port;
    private final String schema;

    public DataSourceProperties(String name, String url, String user, String pass, String port, String schema) {
        this.name = name;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.port = port;
        this.schema = schema;
    }

    public DataSourceProperties(Node database) {
        NamedNodeMap attr = database.getAttributes();

        this.name = attr.getNamedItem("name").getNodeValue();
        this.url = attr.getNamedItem("url").getNodeValue();
        this.user = attr.getNamedItem("user").getNodeValue();
        this.pass = attr.getNamedItem("pass").getNodeValue();
        this.port = attr.getNamedItem("port").getNodeValue();
        this.schema = attr.getNamedItem("schema").getNodeValue();
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getPort() {
        return port;
    }

    public String getSchema() {
        return schema;
    }

    @Override
    public String toString() {
        return String.format("name %s, url %s, port %s, user %s, pass %s, schema %s", name, url, port, user, pass, schema);
    }


}
