package by.epam.carsharing.dao;

import by.epam.carsharing.dao.impl.CarDaoImpl;
import by.epam.carsharing.dao.impl.NewsDaoImpl;
import by.epam.carsharing.dao.impl.UserDaoImpl;

public final class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    private final UserDao userDao = new UserDaoImpl();
    private final NewsDao newsDao = new NewsDaoImpl();
    private final CarDao carDao = new CarDaoImpl();


    public NewsDao getNewsDao() {
        return newsDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CarDao getCarDao() {return carDao;}
}
