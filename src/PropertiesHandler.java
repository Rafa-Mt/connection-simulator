import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

public class PropertiesHandler {
    private String dir;

    public PropertiesHandler(String fileName) {
        dir = MessageFormat.format("src/properties/{0}.properties", fileName);
    }    

    public String Get(String key){
        InputStream input;
        Properties file = new Properties();
        String value;

        try { 
            input = new FileInputStream(dir);
            file.load(input); 
            value = file.getProperty(key);
            input.close();
            return value;
        } 
        catch (Exception ex) { 
            ex.printStackTrace(); 
            return "Error at resolving property";
        }
    }

    public String Get(String key, Object[] args) {
        String rawValue = Get(key);
        return MessageFormat.format(rawValue, args);
    }
}
