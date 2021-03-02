package by.epam.carsharing.command.impl;

import by.epam.carsharing.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToLoginPageCommand implements Command {

    private final static String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            // TODO: add logger;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
