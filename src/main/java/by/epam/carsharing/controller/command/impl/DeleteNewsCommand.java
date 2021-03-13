package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.NewsService;
import by.epam.carsharing.service.ServiceFactory;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteNewsCommand implements Command {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String REFERER = "referer";
    private static final Logger logger = LogManager.getLogger(DeleteNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        try {
            NewsService newsService = serviceFactory.getNewsService();
            newsService.deleteById(id);
        } catch (ServiceException e) {
            logger.error(e);
        }
        response.sendRedirect(request.getHeader(REFERER));
    }
}
