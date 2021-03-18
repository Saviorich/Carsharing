package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.controller.command.CommandName;
import by.epam.carsharing.entity.News;
import by.epam.carsharing.entity.Role;
import by.epam.carsharing.entity.car.Car;
import by.epam.carsharing.entity.user.User;
import by.epam.carsharing.service.exception.ServiceException;
import by.epam.carsharing.service.CarService;
import by.epam.carsharing.service.NewsService;
import by.epam.carsharing.service.ServiceFactory;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class GoToEditPage implements Command {

    private static final Logger logger = LogManager.getLogger(GoToEditPage.class);

    private static final String NEWS_EDIT_PAGE = "/WEB-INF/jsp/news_edit.jsp";
    private static final String CAR_EDIT_PAGE = "/WEB-INF/jsp/car_edit.jsp";
    private static final String LOGIN_PAGE = "/login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        try {
            CommandName commandName = CommandName.valueOf(request.getParameter(RequestParameter.COMMAND).toUpperCase());
            switch (commandName) {
                case GOTONEWSEDITPAGE:
                    processNews(request, response, user);
                    break;
                case GOTOCAREDITPAGE:
                    processCar(request, response, user);
                    break;
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

    private void processCar(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException, ServiceException {
        CarService carService = ServiceFactory.getInstance().getCarService();

        String data_id = request.getParameter(RequestParameter.DATA_ID);
        logger.log(Level.DEBUG, data_id);
        if (data_id != null) {
            Optional<Car> car = carService.getById(
                    Integer.parseInt(request.getParameter(RequestParameter.DATA_ID)));
            request.setAttribute(RequestParameter.DATA_ID, car.get());
        }
        RequestDispatcher requestDispatcher;
        if (user != null && user.getRole() == Role.ADMIN) {
            requestDispatcher = request.getRequestDispatcher(NEWS_EDIT_PAGE);
        } else {
            requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
        }
        requestDispatcher.forward(request, response);
    }

    private void processNews(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException, ServiceException {
        NewsService newsService = ServiceFactory.getInstance().getNewsService();

        String data_id = request.getParameter(RequestParameter.DATA_ID);
        logger.log(Level.DEBUG, data_id);
        if (data_id != null) {
            Optional<News> news = newsService.findNewsById(
                    Integer.parseInt(request.getParameter(RequestParameter.DATA_ID)));
            request.setAttribute(RequestParameter.DATA_ID, news.get());
        }
        RequestDispatcher requestDispatcher;
        if (user != null && user.getRole() == Role.ADMIN) {
            requestDispatcher = request.getRequestDispatcher(NEWS_EDIT_PAGE);
        } else {
            requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}
