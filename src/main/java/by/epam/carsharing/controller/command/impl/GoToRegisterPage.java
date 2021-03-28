package by.epam.carsharing.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.carsharing.util.RequestUtils.processRequest;

public class GoToRegisterPage implements by.epam.carsharing.controller.command.Command {

    private static final Logger logger = LogManager.getLogger(GoToRegisterPage.class);
    private static final String REGISTER_PAGE = "/register";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(REGISTER_PAGE);
        requestDispatcher.forward(request, response);
    }
}
