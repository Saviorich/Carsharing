package by.epam.carsharing.service;

import by.epam.carsharing.service.impl.CarServiceImpl;
import by.epam.carsharing.service.impl.NewsServiceImpl;
import by.epam.carsharing.service.impl.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {}

    private final NewsService newsService = new NewsServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final CarService carService = new CarServiceImpl();

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
}
