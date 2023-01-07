package Utils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class TextReader implements Reader {
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
        String text = textString.replace("#", "");
        List<String> convertedTextList = Arrays.asList(text.split("\\|"));
        try {
            Constructor<?> c = tClass.getDeclaredConstructor(List.class);
            return (T) c.newInstance(convertedTextList);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
