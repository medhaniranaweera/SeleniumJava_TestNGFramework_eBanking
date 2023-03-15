package config;

import tests.LoginPageTest;
import java.io.*;
import java.util.Properties;
public class PropertiesFile {
    static Properties properties = new Properties();
    static String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {
        getProperties();
        setProperties();
    }
    public static void getProperties(){
        try {
            InputStream inputStream = new FileInputStream(projectPath + "/src/test/java/config/config.properties");
            properties.load(inputStream);
//            sets the browser property to the BrowserName parameter of the Test class
            LoginPageTest.browserName = properties.getProperty("browser");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
        }
    }

    public static void setProperties(){
        try {
            OutputStream outputStream = new FileOutputStream(projectPath + "/src/test/java/config/config.properties");
            properties.setProperty("browser", "firefox");
            properties.store(outputStream, "Setting up new browser");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
        }

    }
}

