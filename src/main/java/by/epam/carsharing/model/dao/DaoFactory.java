package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.impl.*;

public final class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
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
}
