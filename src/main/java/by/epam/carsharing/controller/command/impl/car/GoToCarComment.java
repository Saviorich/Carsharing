package by.epam.carsharing.controller.command.impl.car;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.car.CarComment;
import by.epam.carsharing.model.service.CarCommentService;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToCarComment implements Command {

    private static final Logger logger = LogManager.getLogger(GoToCarComment.class);

    private static final String COMMENTS_PAGE = "/WEB-INF/jsp/comments.jsp";
    private static final String ERROR_PAGE = "error.jsp";
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String REFERER = "referer";
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        int carId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        CarCommentService commentService = serviceFactory.getCommentService();
        CarService carService = serviceFactory.getCarService();
        try {
            Car car = carService.getById(carId).get();
            List<CarComment> comments = commentService.getAllByCarId(carId);

            /**
             * If the request came from the orders page,
             * then this ensures that the user can leave a comment.
             * @see HttpServletRequest
             * */
            if (request.getHeader(REFERER).contains(GO_TO_ORDERS_PAGE)) {
                request.setAttribute(RequestParameter.ABLE_TO_COMMENT, true);
            }

            request.setAttribute(RequestParameter.CAR, car);
            request.setAttribute(RequestParameter.DATA, comments);
            dispatcher = request.getRequestDispatcher(COMMENTS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.FATAL, e);
            dispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        dispatcher.forward(request, response);
    }
}
