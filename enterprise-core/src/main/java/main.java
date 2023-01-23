import Builders.DatabaseBuilder;
import Models.Film;
import Queries.FilmQuery;
import Utils.JSONReader;
import Utils.TextReader;
import Utils.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.reflections.Reflections.log;

public class main {
    public static void main(String[] args) throws IOException {
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
//        Film filmA = XMLReader.getInstance().deserialiseObject(XmlString, Film.class);
//        Film filmB = JSONReader.getInstance().deserialiseObject(JsonString, Film.class);
//
//        System.out.println(filmA.toString());
//        System.out.println(JSONReader.getInstance().serialiseObject(film));

        DatabaseBuilder databaseBuilder = new DatabaseBuilder("file");
        FilmQuery filmQuery = new FilmQuery(databaseBuilder.getDataSourceConnector());

        var getByidThing = filmQuery.getById(10016);

        System.out.println(getByidThing.toString());

        //        TextReader textReader = TextReader.getInstance();
//        System.out.println(textReader.serialiseObject(film));
//        String text = "#1|Something|1|me|stariis|reviewssasadasdasdsdfsfsfsdasds";
//        Film filmC = textReader.deserialiseObject(text, Film.class);
//
//        System.out.println(filmC.toString());

    }
}