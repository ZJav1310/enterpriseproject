package Utils;

import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;

public interface Reader {
    Element readFile(String FilePath) throws ParserConfigurationException;
//    NodeList getNodeListByTagName(String elementTagName);
//    Element getDocumentRoot();
}
