package by.epam.carsharing.controller.command.impl.news;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.NewsService;
import by.epam.carsharing.model.service.ServiceProvider;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deletes {@link by.epam.carsharing.model.entity.News} from the database
 * @see Command
 */
public class DeleteNewsCommand implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String REFERER = "referer";
    private static final Logger logger = LogManager.getLogger(DeleteNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        try {
            NewsService newsService = SERVICE_PROVIDER.getNewsService();
            newsService.deleteById(id);
        } catch (ServiceException e) {
            logger.error(e);
        }
        response.sendRedirect(request.getHeader(REFERER));
    }
}
