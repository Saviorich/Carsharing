package by.epam.carsharing.exception;

public class ServiceException extends Exception {

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Exception e) {

    }
}
