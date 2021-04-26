package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.ServiceProvider;
import by.epam.carsharing.model.service.UserService;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Take {@link User} from database and adds him to the session
 * and redirects to the referer from the request
 * @see Command
 * @see HttpServletRequest
 * @see HttpServletResponse
 * @see HttpSession
 */
public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String GO_TO_LOGIN_PAGE = "Controller?command=gotologinpage&error=%s&validation=%s";
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandResult = GO_TO_NEWS_PAGE;

        String email;
        String password;

        email = request.getParameter(RequestParameter.EMAIL);
        password = request.getParameter(RequestParameter.PASSWORD);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        HttpSession session = request.getSession();

        Optional<User> user;

        try {
            user = userService.findUserByEmailAndPassword(email, password);
            if (!user.isPresent()) {
                throw new InvalidDataException();
            }
            session.setAttribute(SessionAttribute.USER, user.get());
        } catch (ServiceException e) {
            logger.error(Level.FATAL, e);
            commandResult = String.format(GO_TO_LOGIN_PAGE, true, null);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_LOGIN_PAGE, null, e.getMessage());
        }
        response.sendRedirect(commandResult);
    }
}


