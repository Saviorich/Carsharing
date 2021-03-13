package by.epam.carsharing.command.impl;

import by.epam.carsharing.command.Command;
import by.epam.carsharing.entity.News;
import by.epam.carsharing.entity.Role;
import by.epam.carsharing.entity.user.User;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.NewsService;
import by.epam.carsharing.service.ServiceFactory;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class GoToNewsEditPage implements Command {

    private static final Logger logger = LogManager.getLogger(GoToNewsEditPage.class);

    private static final String NEWS_EDIT_PAGE = "/WEB-INF/jsp/news_edit.jsp";
    private static final String LOGIN_PAGE = "/login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        try {
            NewsService newsService = ServiceFactory.getInstance().getNewsService();

            String data_id = request.getParameter(RequestParameter.DATA_ID);
            logger.log(Level.DEBUG, data_id);
            if (data_id != null) {
                Optional<News> news = newsService.findNewsById(
                        Integer.parseInt(request.getParameter(RequestParameter.DATA_ID)));
                request.setAttribute(RequestParameter.DATA_ID, news.get());
            }
            RequestDispatcher requestDispatcher;
            if (user != null && user.getRole() == Role.ADMIN) {
                requestDispatcher = request.getRequestDispatcher(NEWS_EDIT_PAGE);
            } else {
                requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            }
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
