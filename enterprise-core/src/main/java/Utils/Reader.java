package Utils;

import org.w3c.dom.Element;

public interface Reader {
    String serialiseObject(Object input);
    <T> T deserialiseObject(String inputString, Class<T> tClass);
}
