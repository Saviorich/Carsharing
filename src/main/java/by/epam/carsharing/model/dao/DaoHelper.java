package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.impl.*;

public final class DaoHelper {
    private final static DaoHelper instance = new DaoHelper();

    public static DaoHelper getInstance() {
        return instance;
    }

    private final UserDao userDao = new UserDaoImpl();
    private final NewsDao newsDao = new NewsDaoImpl();
    private final CarDao carDao = new CarDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final UserDetailDao detailDao = new UserDetailDaoImpl();
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

    public UserDetailDao getUserDetailsDao() {
        return detailDao;
    }

    public PassportDao getPassportDao() {
        return passportDao;
    }

}
