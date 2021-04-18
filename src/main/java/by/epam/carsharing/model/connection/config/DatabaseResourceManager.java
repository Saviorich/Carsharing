package by.epam.carsharing.model.connection.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that read {@link Properties} from database.properties file
 */
public final class DatabaseResourceManager {

    private static final DatabaseResourceManager INSTANCE = new DatabaseResourceManager();
    private static final Logger logger = LogManager.getLogger(DatabaseResourceManager.class);

    private static final String PROPERTIES = "database.properties";

    private Properties properties;

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
        return INSTANCE;
    }

    /**
     * Finds a value by key and returns it
     * @return the {@link String} value of property
     * */
    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
