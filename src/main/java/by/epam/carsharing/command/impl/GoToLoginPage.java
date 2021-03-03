package by.epam.carsharing.command.impl;

import by.epam.carsharing.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToLoginPage implements Command {

    private static final Logger logger = LogManager.getLogger(GoToLoginPage.class);

    //private final static String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    private static final String LOGIN_PAGE = "/login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
            // TODO: redirect to error page
        }
    }
}
