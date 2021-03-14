package by.epam.carsharing.exception;

public class ConnectionPoolException extends Exception {
    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {

    }

    public ConnectionPoolException(String message, Exception e) {

    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
