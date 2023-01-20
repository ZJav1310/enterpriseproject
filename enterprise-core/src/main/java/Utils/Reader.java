package Utils;

public interface Reader {
    <T> String serialiseObject(T input);
    <T> T deserialiseObject(String inputString, Class<T> tClass);
    String getAcceptedMimeType();
    <T> boolean isValidInput(String input, Class<T> tClass);
}
