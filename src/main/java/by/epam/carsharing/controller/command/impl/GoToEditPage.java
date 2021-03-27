package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.controller.command.CommandName;
import by.epam.carsharing.model.entity.News;
import by.epam.carsharing.model.entity.Role;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.NewsService;
import by.epam.carsharing.model.service.ServiceFactory;
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

        CarService carService = ServiceFactory.getInstance().getCarService();
        NewsService newsService = ServiceFactory.getInstance().getNewsService();

        String data_id = request.getParameter(RequestParameter.DATA_ID);

        try {
            CommandName commandName = CommandName.valueOf(request.getParameter(RequestParameter.COMMAND).toUpperCase());
            switch (commandName) {
                case GOTOCAREDITPAGE:
                    if (data_id != null) {
                        Optional<Car> car = carService.getById(
                                Integer.parseInt(data_id));
                        request.setAttribute(RequestParameter.DATA, car.get());
                    }
                    executeCommandResult(request, response, user, CAR_EDIT_PAGE);
                    break;
                case GOTONEWSEDITPAGE:
                    if (data_id != null) {
                        Optional<News> news = newsService.findNewsById(
                                Integer.parseInt(data_id));
                        request.setAttribute(RequestParameter.DATA, news.get());
                    }
                    executeCommandResult(request, response, user, NEWS_EDIT_PAGE);
                    break;
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }


    private void executeCommandResult(HttpServletRequest request, HttpServletResponse response, User user, String page)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        if (user != null && user.getRole() == Role.ADMIN) {
            requestDispatcher = request.getRequestDispatcher(page);
        } else if (user == null) {
            requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
        } else {
            throw new ServletException("User is not authenticated");
        }
        requestDispatcher.forward(request, response);
    }
}
