import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configProperties {

    public static Properties property;
    private static String configpath="C:\\Users\\User\\IdeaProjects\\Musala1\\src\\Configuration_file\\configsetting.properties";

    public static void initialazePropertyFile()  {
        property = new Properties();
        try {
            InputStream instm = new FileInputStream(configpath);
            property.load(instm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
