package Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReader implements Reader {

    // serialize object to JSON
    public String serialiseObject(Object input) {
        try {
            return new ObjectMapper().writeValueAsString(input);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // deserialize JSON back to object
    public <T> T deserialiseObject(String JsonString, Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(JsonString, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
