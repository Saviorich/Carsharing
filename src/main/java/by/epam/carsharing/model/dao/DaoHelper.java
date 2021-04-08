package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.impl.*;
import by.epam.carsharing.model.entity.car.CarComment;

public final class DaoHelper {
    private final static DaoHelper instance = new DaoHelper();

    public static DaoHelper getInstance() {
        return instance;
    }

    private final UserDao userDao = new UserDaoImpl();
    private final NewsDao newsDao = new NewsDaoImpl();
    private final CarDao carDao = new CarDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final PassportDao passportDao = new PassportDaoImpl();
    private final PaymentDao paymentDao = new PaymentDaoImpl();
    private final CarCommentDao carCommentDao = new CarCommentDaoImpl();


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

    public PassportDao getPassportDao() {
        return passportDao;
    }

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public CarCommentDao getCarCommentDao() {
        return carCommentDao;
    }
}
