package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.dao.impl.*;

import java.sql.Connection;
import java.sql.SQLException;

public final class DaoHelper {
    private final static DaoHelper instance = new DaoHelper();

    public static DaoHelper getInstance() {
        return instance;
    }

    private final UserDao userDao = new UserDaoImpl();
    private final NewsDao newsDao = new NewsDaoImpl();
    private final CarDao carDao = new CarDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final UserDetailsDao detailsDao = new UserDetailsDaoImpl();
    private final PassportDao passportDao = new PassportDaoImpl();


    public NewsDao getNewsDao() {
        return newsDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CarDao getCarDao() {
        return carDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public UserDetailsDao getUserDetailsDao() {
        return detailsDao;
    }

    public PassportDao getPassportDao() {
        return passportDao;
    }

    public void startTransaction(Connection connection) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public void endTransaction(Connection connection) throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public void cancelTransaction(Connection connection) throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
