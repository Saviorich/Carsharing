package by.epam.carsharing.exception;

import java.sql.SQLException;

public class DaoException extends Exception {

    public DaoException() {
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Exception e) {

    }

    public DaoException(Exception e) {

    }
}
