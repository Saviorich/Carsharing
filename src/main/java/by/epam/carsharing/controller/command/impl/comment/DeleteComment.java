package by.epam.carsharing.controller.command.impl.comment;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.service.CarCommentService;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteComment implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteComment.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = request.getHeader(REFERER);

        int commentId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        CarCommentService commentService = serviceFactory.getCommentService();
        try {
            commentService.deleteById(commentId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            commandResult += "&error=true";
        }
        response.sendRedirect(commandResult);
    }
}
