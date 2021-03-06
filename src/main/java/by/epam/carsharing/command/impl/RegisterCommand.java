package by.epam.carsharing.command.impl;

import by.epam.carsharing.command.Command;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.ServiceFactory;
import by.epam.carsharing.service.UserService;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterCommand implements Command {
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";
    private static final String GO_TO_REGISTER_PAGE = "Controller?command=gotoregisterpage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);

        HttpSession session = request.getSession();
        UserService userService = serviceFactory.getUserService();

        try {
            userService.registerUser(email, password);
            response.sendRedirect(GO_TO_NEWS_PAGE);
            session.setAttribute(SessionAttribute.USER, userService.findUserByEmailAndPassword(email, password).get());
        } catch (ServiceException e) {
            session.setAttribute(SessionAttribute.ERROR, true);
            response.sendRedirect(GO_TO_REGISTER_PAGE);
        }
    }
}
