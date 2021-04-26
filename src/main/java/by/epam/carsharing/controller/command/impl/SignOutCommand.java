package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.util.SessionAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Removes user from the session and redirects to the news page
 * @see Command
 * @see javax.servlet.http.HttpSession
 * @see HttpServletResponse
 */
public class SignOutCommand implements Command {
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(SessionAttribute.USER);
        response.sendRedirect(GO_TO_NEWS_PAGE);
    }
}
