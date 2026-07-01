package factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties PROPERTIES = loadProperties();

    public static String getProperty(String key){
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }
        return PROPERTIES.getProperty(key);
    }

    public static int getPropertyAsNumber(String key){
        return Integer.parseInt(getProperty(key));
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream("Configs/configurations.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("Configs/configurations.properties was not found on the classpath");
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load Configs/configurations.properties", e);
        }
    }

}
