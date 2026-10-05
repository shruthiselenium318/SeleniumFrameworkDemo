package utilities;

import java.io.*;
import java.util.Properties;

public class fileUtil {


    private Properties properties=null;


    public fileUtil(String filepath){

        properties=new Properties();
         try (InputStream file =
             getClass().getClassLoader().getResourceAsStream(filepath)) {
            System.out.println("This si the" + filepath);
        if (file == null) {
            System.out.println("Could not find properties file: " + filepath);
            throw new RuntimeException(
                "Could not find properties file: " + filepath
            );

        }

        properties.load(file);
        System.out.println("Properties file loaded");

    } catch (Exception e) {
        throw new RuntimeException(
            "Failed to load: " + filepath, e
        );
    }

    }
    public String GetProperty(String Key)
    {
        return properties.get(Key).toString();
    }



}
