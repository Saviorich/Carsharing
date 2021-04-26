package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.carsharing.util.RequestUtil.processRequestErrors;

/**
 * Forwards to the register page
 * @see Command
 * @see RequestDispatcher
 */
public class GoToRegisterPage implements Command {

    private static final String REGISTER_PAGE = "/register";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequestErrors(request);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(REGISTER_PAGE);
        requestDispatcher.forward(request, response);
    }
}
