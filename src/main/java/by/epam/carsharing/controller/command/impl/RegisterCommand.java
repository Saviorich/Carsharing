package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.user.Passport;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.entity.user.UserDetail;
import by.epam.carsharing.model.service.ServiceProvider;
import by.epam.carsharing.model.service.UserService;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.DateUtil;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creates {@link User} and saves it in the database and in the session
 * @see Command
 * @see HttpSession
 */
public class RegisterCommand implements Command {
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    private static final DateUtil DATE_UTILS = new DateUtil();
    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";
    private static final String GO_TO_REGISTER_PAGE = "Controller?command=gotoregisterpage&error=%s&validation=%s";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = GO_TO_NEWS_PAGE;

        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);

        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String secondName = request.getParameter(RequestParameter.SECOND_NAME);
        String middleName = request.getParameter(RequestParameter.MIDDLE_NAME);
        String phoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);

        String passportNumber = request.getParameter(RequestParameter.PASSPORT_NUMBER);
        String identificationNumber = request.getParameter(RequestParameter.IDENTIFICATION_NUMBER);
        String issueDateParameter = request.getParameter(RequestParameter.ISSUE_DATE);

        HttpSession session = request.getSession();
        UserService userService = SERVICE_PROVIDER.getUserService();

        try {
            Date issueDate = DATE_UTILS.parseDate(issueDateParameter);
            Passport passport = new Passport(passportNumber, identificationNumber, issueDate);
            UserDetail details = new UserDetail(null, passportNumber, phoneNumber, firstName, secondName, middleName);
            userService.registerUser(email, password, details, passport);
            User user = userService.findUserByEmailAndPassword(email, password).get();
            session.setAttribute(SessionAttribute.USER, user);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_REGISTER_PAGE, true, null);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_REGISTER_PAGE, null, true);
        }
        response.sendRedirect(commandResult);
    }
}
