package by.epam.carsharing.controller.command.impl.comment;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.service.CarCommentService;
import by.epam.carsharing.model.service.ServiceProvider;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deletes {@link by.epam.carsharing.model.entity.car.CarComment} from database
 * @see Command
 */
public class DeleteComment implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteComment.class);
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String ERROR_PARAMETER = "&error=true";
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = request.getHeader(REFERER);

        int commentId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        CarCommentService commentService = SERVICE_PROVIDER.getCommentService();
        try {
            commentService.deleteById(commentId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            commandResult += ERROR_PARAMETER;
        }
        response.sendRedirect(commandResult);
    }
}
