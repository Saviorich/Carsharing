package by.epam.carsharing.model.service;

import by.epam.carsharing.model.service.impl.*;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {}

    private final NewsService newsService = new NewsServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final CarService carService = new CarServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();
    private final CarCommentService commentService = new CarCommentServiceImpl();

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

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public CarCommentService getCommentService() {
        return commentService;
    }
}
