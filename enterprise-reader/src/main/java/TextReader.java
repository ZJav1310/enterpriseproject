import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

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
    public String serialiseObject(Object input) {
        try {
            return input.getClass().getDeclaredMethod("toString").invoke(input).toString();

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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
