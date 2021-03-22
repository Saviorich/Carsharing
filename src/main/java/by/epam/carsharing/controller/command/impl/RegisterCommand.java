package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.entity.user.UserDetails;
import by.epam.carsharing.model.service.UserDetailsService;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.UserService;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterCommand implements Command {
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";
    private static final String GO_TO_REGISTER_PAGE = "Controller?command=gotoregisterpage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);

        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String secondName = request.getParameter(RequestParameter.SECOND_NAME);
        String middleName = request.getParameter(RequestParameter.MIDDLE_NAME);
        String phoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);

        HttpSession session = request.getSession();
        UserService userService = serviceFactory.getUserService();
        UserDetailsService detailsService = serviceFactory.getUserDetailsService();

        try {
            userService.registerUser(email, password);
            User user = userService.findUserByEmailAndPassword(email, password).get();
            session.setAttribute(SessionAttribute.USER, user);
            UserDetails details = new UserDetails(user.getId(), phoneNumber, firstName, secondName, middleName);
            detailsService.add(details);
            response.sendRedirect(GO_TO_NEWS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, e);
            session.setAttribute(SessionAttribute.ERROR, true);
            response.sendRedirect(GO_TO_REGISTER_PAGE);
        }
    }
}
