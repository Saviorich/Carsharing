package by.epam.carsharing.controller.command.impl.car;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.controller.command.impl.news.GoToNewsPage;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.ServiceFactory;
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
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        try {
            requestDispatcher = request.getRequestDispatcher(CARS_PAGE);
            CarService carService = serviceFactory.getCarService();
            List<Car> cars = carService.getAll();
            request.setAttribute(RequestParameter.CARS, cars);
        } catch (ServiceException e) {
            logger.error(e);
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}
