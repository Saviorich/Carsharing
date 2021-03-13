package by.epam.carsharing.command.impl;

import by.epam.carsharing.command.Command;
import by.epam.carsharing.entity.News;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.NewsService;
import by.epam.carsharing.service.ServiceFactory;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToNewsPage implements Command {

    private static final Logger logger = LogManager.getLogger(GoToNewsPage.class);

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String NEWS_PAGE = "/WEB-INF/jsp/news.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(NEWS_PAGE);

        try {
            NewsService newsService = serviceFactory.getNewsService();
            List<News> news = newsService.getAll();
            request.setAttribute(RequestParameter.NEWS, news);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            // TODO: redirect to error page
        }
    }
}