package by.epam.carsharing.controller.command.impl.order;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static by.epam.carsharing.util.RequestUtils.processRequest;

public class GoToOrderPage implements Command {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(GoToOrderPage.class);
    private static final String ORDER_PAGE = "/WEB-INF/jsp/order.jsp";
    private static final String LOGIN_PAGE = "/login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;

        try {
            CarService carService = serviceFactory.getCarService();
            Optional<Car> car = carService.getById(Integer.parseInt(request.getParameter(RequestParameter.DATA_ID)));

            car.ifPresent(value -> request.setAttribute(RequestParameter.CAR, value));

            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
            if (user != null) {
                requestDispatcher = request.getRequestDispatcher(ORDER_PAGE);
            } else {
                requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            }
            processRequest(request);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}