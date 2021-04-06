package by.epam.carsharing.model.connection.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseResourceManager {

    private static DatabaseResourceManager instance = new DatabaseResourceManager();
    private static final Logger logger = LogManager.getLogger(DatabaseResourceManager.class);

    private static final String PROPERTIES = "database.properties";

    private Properties properties;

    /**
    * Initialize a {@link Properties} object
    * Read properties from database.properties file with using {@link ClassLoader}
    */
    {
        ClassLoader loader = DatabaseResourceManager.class.getClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream(PROPERTIES)) {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);
                this.properties = properties;
            } else {
                logger.fatal("database.properties not found");
                throw new IOException("database.properties not found");
            }
        } catch (IOException e) {
            logger.fatal("IOException in DatabaseResourceManager class");
            throw new RuntimeException(e);
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
