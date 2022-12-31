package Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLReader implements Reader {

    public Element readFile(String XMLFilePath) throws ParserConfigurationException {

        try {
            var factory = DocumentBuilderFactory.newInstance();
            // Avoid XXE attacks
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var builder = factory.newDocumentBuilder();
            Document document = builder.parse(XMLFilePath);
            document.getDocumentElement().normalize();
            return document.getDocumentElement();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
