import java.io.File;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        String[] foundPluginArray = (new File("C:\\Users\\ZTedd\\IdeaProjects\\enterpriseproject\\enterprise-reader\\src\\main\\java\\plugins")).list();

        for(String name : foundPluginArray){
            System.out.println(name);
        }
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);

        String userDirectory = Paths.get("")
                .toAbsolutePath()
                .toString();

        String CURRENT_DIR = "plugin";
        System.out.println(userDirectory);
        //assertTrue(userDirectory.endsWith(CURRENT_DIR));
        String userDirectory1 = FileSystems.getDefault()
                .getPath("")
                .toAbsolutePath()
                .toString();
        System.out.println(userDirectory1);
    }
}
