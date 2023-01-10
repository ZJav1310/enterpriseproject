package Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReader implements Reader {
    private static volatile JSONReader instance = null;
    private JSONReader(){}
    public static JSONReader getInstance(){
        if(instance == null){
            synchronized (JSONReader.class){
                if(instance == null){
                    instance = new JSONReader();
                }
            }
        }
        return instance;
    }
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
    public <T> boolean isValidInput(String jsonString, Class<T> tClass){
        try{
            if(deserialiseObject(jsonString, tClass) != null)
                return true;
        } catch (Exception ignored) {
        }
        return false;
    }
    public String getAcceptedMimeType() {
        return "application/json";
    }
}
