package by.epam.carsharing.controller.command.impl.news;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.NewsService;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditNewsCommand implements Command {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(EditNewsCommand.class);
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";
    private static final String GO_TO_NEWS_EDIT_PAGE = "Controller?command=gotonewseditpage&data_id=%d&validation=%s&error=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = GO_TO_NEWS_PAGE;

        int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
        String header = request.getParameter(RequestParameter.HEADER_EDITOR);
        String content = request.getParameter(RequestParameter.CONTENT_EDITOR);
        String imagePath = (String) request.getAttribute(RequestParameter.IMAGE_PATH);

        try {
            NewsService newsService = serviceFactory.getNewsService();
            newsService.update(id, header, content, imagePath);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_NEWS_EDIT_PAGE, id, null, e.getMessage());
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_NEWS_EDIT_PAGE, id, e.getMessage(), null);
        }
        response.sendRedirect(commandResult);
    }
}
