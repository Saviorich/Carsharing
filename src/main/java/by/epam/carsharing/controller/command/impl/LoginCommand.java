package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.UserService;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String GO_TO_LOGIN_PAGE = "Controller?command=gotologinpage";
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login;
        String password;

        login = request.getParameter(RequestParameter.EMAIL);
        password = request.getParameter(RequestParameter.PASSWORD);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        HttpSession session = request.getSession();

        Optional<User> user;

        try {
            user = userService.findUserByEmailAndPassword(login, password);

            if (!user.isPresent()) {
                session.setAttribute(SessionAttribute.ERROR, true);
                response.sendRedirect(GO_TO_LOGIN_PAGE);
                return;
            }

            session.setAttribute(SessionAttribute.USER, user.get());
            response.sendRedirect(GO_TO_NEWS_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);

            session.setAttribute(SessionAttribute.ERROR, true);
            response.sendRedirect(GO_TO_LOGIN_PAGE);
        }
    }
}


