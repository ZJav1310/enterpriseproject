package Models;

public class ServerConfig {
    private String name;

//    public ServerConfig(){
//        System.out.println("Getting server config.");
//        try {
//            getServerConfig();
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public String getName() {
        return name;
    }

    //    TODO can change this to XMLMapper
//    private void getServerConfig(){
//        String FILENAME = "/src/Configuration/ServerConfig.xml";
//        String ROOTPATH = new File("").getAbsolutePath();
//        File file = new File(ROOTPATH + FILENAME);
//        XmlMapper xmlMapper = new XmlMapper();
//        try {
//            ServerConfig sc = xmlMapper.readValue(file, ServerConfig.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private void getServerConfig() throws ParserConfigurationException {
//        Reader reader = new XMLReader();
//        String FILENAME = "/src/main/java/Configuration/ServerConfig.xml";
//        String ROOTPATH = new File("").getAbsolutePath();
//        System.out.println(ROOTPATH);
//        reader.readFile(ROOTPATH + FILENAME);
//        NodeList dbNodeList = reader.getNodeListByTagName("DataSource");
//
//        NamedNodeMap attr = dbNodeList.item(0).getAttributes();
//
//        name = attr.getNamedItem("name").getNodeValue();
//    }

}
