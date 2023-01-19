package Utils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
@Deprecated
public class TextReader implements Reader {

    private static volatile TextReader instance = null;
    private TextReader(){}
    public static TextReader getInstance(){
        if(instance == null){
            synchronized (TextReader.class){
                if(instance == null){
                    instance = new TextReader();
                }
            }
        }
        return instance;
    }
    @Override
    public <T> String serialiseObject(T input) {

        // Need to check if the input is in an array etc
        if(input.getClass().isArray()){
            return Arrays.toString((Object[]) input);
        }
        if(input instanceof List){
            return Arrays.toString(((List) input).toArray());
        }
        return input.toString();

    }

    @Override
    public <T> T deserialiseObject(String textString, Class<T> tClass) {
        try {
            Constructor<?> c = tClass.getDeclaredConstructor(List.class);
            return (T) c.newInstance(Arrays.asList(textString.replace("#", "").split("\\|")));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public <T> boolean isValidInput(String textString, Class<T> tClass){
        try{
            if(deserialiseObject(textString, tClass) != null)
                return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public String getAcceptedMimeType() {
        return "text/html";
    }

}
