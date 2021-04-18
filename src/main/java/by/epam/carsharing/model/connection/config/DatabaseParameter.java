package by.epam.carsharing.model.connection.config;

/**
* Contains keys for database properties that used in {@link by.epam.carsharing.model.connection.ConnectionPool}
* */
public final class DatabaseParameter {
    public static final String DRIVER = "driver";
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String POOL_SIZE = "pool_size";

    private DatabaseParameter() {}
}
