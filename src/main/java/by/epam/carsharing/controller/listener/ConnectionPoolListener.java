package by.epam.carsharing.controller.listener;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.connection.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(ConnectionPoolListener.class);

    /**
     * Initializes connection pool when application is starting
     * @see ConnectionPool
     * */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            logger.fatal("Can't initialize pool data.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Disposing the connection pool when application is finished
     * @see ConnectionPool
     * */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
    }
}
