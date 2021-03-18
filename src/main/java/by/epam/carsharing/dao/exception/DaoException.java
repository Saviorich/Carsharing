package by.epam.carsharing.dao.exception;

import java.sql.SQLException;

public class DaoException extends Exception {

    public DaoException() {
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }

    public DaoException(Exception e) {
        super(e);
    }
}
