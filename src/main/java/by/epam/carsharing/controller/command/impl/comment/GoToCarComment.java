package by.epam.carsharing.controller.command.impl.comment;

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

import static by.epam.carsharing.util.RequestUtil.processRequestErrors;

public class GoToCarComment implements Command {

    private static final Logger logger = LogManager.getLogger(GoToCarComment.class);

    private static final String COMMENTS_PAGE = "/WEB-INF/jsp/comments.jsp";
    private static final String ERROR_PAGE = "error.jsp";
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String REFERER = "referer";
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage";
    private static final CarCommentService COMMENT_SERVICE = serviceFactory.getCommentService();

    private static final int RECORDS_PER_PAGE = 3;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        int carId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        CarService carService = serviceFactory.getCarService();
        try {
            /*
              If the request came from the orders page,
              then this ensures that the user can leave a comment.
             */
            if (request.getHeader(REFERER).contains(GO_TO_ORDERS_PAGE)) {
                request.setAttribute(RequestParameter.ABLE_TO_COMMENT, true);
            }

            Car car = carService.getById(carId).get();
            request.setAttribute(RequestParameter.CAR, car);

            processRequestErrors(request);
            processPage(carId, request);
            dispatcher = request.getRequestDispatcher(COMMENTS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.FATAL, e);
            dispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        dispatcher.forward(request, response);
    }

    private void processPage(int carId, HttpServletRequest request) throws ServiceException {
        int currentPage = Integer.parseInt(request.getParameter(RequestParameter.CURRENT_PAGE));

        List<CarComment> comments = COMMENT_SERVICE.getCommentsForPage(carId, RECORDS_PER_PAGE, currentPage);
        request.setAttribute(RequestParameter.DATA, comments);

        int records = COMMENT_SERVICE.getDataAmount(carId);

        // Calculates actual pages amount
        int pagesAmount = (int) Math.ceil(records / (float) RECORDS_PER_PAGE);

        request.setAttribute(RequestParameter.PAGES_AMOUNT, pagesAmount);
        request.setAttribute(RequestParameter.CURRENT_PAGE, currentPage);
    }
}
