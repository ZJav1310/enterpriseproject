import com.fasterxml.jackson.xml.XmlMapper;

import java.io.IOException;

public class XMLReader implements Reader {
    private static volatile XMLReader instance = null;
    private XMLReader(){}
    public static XMLReader getInstance(){
        if(instance == null){
            synchronized (XMLReader.class){
                if(instance == null){
                    instance = new XMLReader();
                }
            }
        }
        return instance;
    }
    // serialize object to XML
    public String serialiseObject(Object input){
        try {
            return new XmlMapper().writeValueAsString(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserialiseObject(String XMLString, Class<T> tClass){
        try {
            return new XmlMapper().readValue(XMLString, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public <T> boolean isValidInput(String xmlString, Class<T> tClass){
        try{
            if(deserialiseObject(xmlString, tClass) != null)
                return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public String getAcceptedMimeType() {
        return "application/xml";
    }
}
