package by.epam.carsharing.command.impl;

import by.epam.carsharing.command.Command;
import by.epam.carsharing.entity.user.User;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.ServiceFactory;
import by.epam.carsharing.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login;
        String password;

        login = request.getParameter("email");
        password = request.getParameter("password");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        Optional<User> user;

        try {
            user = userService.findUserByEmailAndPassword(login, password);

            if (!user.isPresent()) {
                response.sendRedirect("Controller?command=gotomainpage&message=error1");
                return;
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            response.sendRedirect("Controller?command=gotomainpage");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            response.sendRedirect("Controller?command=gotomainpage&message=error2");
        }
    }
}


