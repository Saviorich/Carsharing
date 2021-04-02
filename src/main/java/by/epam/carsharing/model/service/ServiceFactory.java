package by.epam.carsharing.model.service;

import by.epam.carsharing.model.service.impl.*;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {}

    private final NewsService newsService = new NewsServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final CarService carService = new CarServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final UserDetailService detailService = new UserDetailServiceImpl();
    private final PassportService passportService = new PassportServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public UserService getUserService() {
        return userService;
    }

    public CarService getCarService() {
        return carService;
    }

    public OrderService getOrderService() {return orderService; }

    public UserDetailService getUserDetailService() {
        return detailService;
    }

    public PassportService getPassportService() {
        return passportService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }
}
