package Utils;

import com.fasterxml.jackson.xml.XmlMapper;
import java.io.IOException;

public class XMLReader implements Reader {

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

}
