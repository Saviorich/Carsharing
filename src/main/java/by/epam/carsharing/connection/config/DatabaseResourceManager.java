package by.epam.carsharing.connection.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseResourceManager {

    private static DatabaseResourceManager instance = new DatabaseResourceManager();

    private Properties properties;

    /**
    * Initialize a {@link Properties} object
    * Read properties from database.properties file with using ClassLoader
    */
    {
        ClassLoader loader = DatabaseResourceManager.class.getClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("database.properties")) {
            if (inputStream == null) {
                // LOGGER.error("database.properties not found");
                // throw exception
            }
            Properties properties = new Properties();
            properties.load(inputStream);
            this.properties = properties;
        } catch (IOException e) {
            // TODO: add logger
            //  throw Exception;
        }
    }

    private DatabaseResourceManager() {}

    public static DatabaseResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
