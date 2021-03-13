package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.entity.car.Car;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.CarService;
import by.epam.carsharing.service.ServiceFactory;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToCarPage implements Command {

    private static final Logger logger = LogManager.getLogger(GoToNewsPage.class);

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CARS_PAGE = "/WEB-INF/jsp/cars.jsp";
    private static final String LOGIN_PAGE = "/login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(CARS_PAGE);
            CarService carService = serviceFactory.getCarService();
            List<Car> cars = carService.getAll();
            request.setAttribute(RequestParameter.CARS, cars);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
