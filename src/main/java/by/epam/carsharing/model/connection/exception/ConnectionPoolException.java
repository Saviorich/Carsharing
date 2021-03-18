package by.epam.carsharing.model.connection.exception;

public class ConnectionPoolException extends Exception {
    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
