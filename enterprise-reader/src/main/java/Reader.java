public interface Reader {
    String serialiseObject(Object input);
    <T> T deserialiseObject(String inputString, Class<T> tClass);
    String getAcceptedMimeType();
    <T> boolean isValidInput(String input, Class<T> tClass);
}
