package Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        throw new UnsupportedOperationException("Can not deserialize text yet.");
    }

}
