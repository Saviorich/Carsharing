package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.News;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.NewsService;
import by.epam.carsharing.model.service.ServiceFactory;
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

public class AddNewsCommand implements Command {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(AddNewsCommand.class);
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = ((User)session.getAttribute(SessionAttribute.USER)).getId();
        String header = request.getParameter(RequestParameter.HEADER_EDITOR);
        String content = request.getParameter(RequestParameter.CONTENT_EDITOR);
        String imagePath = (String) request.getAttribute(RequestParameter.IMAGE_PATH);

        try {
            NewsService newsService = serviceFactory.getNewsService();
            News news = new News(userId, header, content, imagePath);
            newsService.add(news);
            response.sendRedirect(GO_TO_NEWS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
