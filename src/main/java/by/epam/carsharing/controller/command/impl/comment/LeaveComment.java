package by.epam.carsharing.controller.command.impl.comment;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.car.CarComment;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.CarCommentService;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LeaveComment implements Command {

    private static final Logger logger = LogManager.getLogger(LeaveComment.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String GO_TO_COMMENTS_PAGE = "Controller?command=gotocarcommentspage&data_id=%d&error=%s&validation=%s";
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = request.getHeader(REFERER);

        String content = request.getParameter(RequestParameter.CONTENT_EDITOR);
        int carId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        CarService carService = serviceFactory.getCarService();
        CarCommentService commentService = serviceFactory.getCommentService();
        try {
            Car car = carService.getById(carId).get();
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
            CarComment comment = new CarComment(user, car, content);
            commentService.add(comment);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_COMMENTS_PAGE, carId, true, null);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_COMMENTS_PAGE, carId, null, e.getMessage());
        }
        response.sendRedirect(commandResult);
    }
}
