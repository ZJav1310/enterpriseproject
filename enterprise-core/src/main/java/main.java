//import Builders.DatabaseBuilder;
//import Models.Film;
//import Queries.FilmQuery;
//import Utils.JSONReader;
//import Utils.TextReader;
//import Utils.XMLReader;
//
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.IOException;
//
//public class main {
//    public static void main(String args[]) throws IOException {
//        XMLReader reader;
//        String PATH = null,
//                JsonString,
//                XmlString = "";
//
//        Film film = new Film(1, "Something", 1, "me", "stars", "reviewssasadasdadasds");
//
//        JsonString = "{\"id\":1,\"title\":\"Something\",\"year\":1,\"director\":\"me\",\"stars\":\"stars\",\"review\":\"reviewsss\"}\n";
//        XmlString = "<Film xmlns=\"\"><id>1</id><title>Something</title><year>1</year><director>me</director><stars>stars</stars><review>reviewsss</review></Film>\n";
//
//        Film filmA = new XMLReader().deserialiseObject(XmlString, Film.class);
//        Film filmB = new JSONReader().deserialiseObject(JsonString, Film.class);
//
//        System.out.println(filmB.toString());
//        System.out.println(new JSONReader().serialiseObject(film));
//
//
//        try {
//            DatabaseBuilder databaseBuilder = new DatabaseBuilder();
//            FilmQuery filmQuery = new FilmQuery(databaseBuilder.getDataSourceConnector());
//
//            var getByidThing = filmQuery.getById(10015);
//
//            System.out.println(getByidThing.toString());
//
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        }
//
//        TextReader textReader = new TextReader();
//        System.out.println(textReader.serialiseObject(film));
//    }
//}