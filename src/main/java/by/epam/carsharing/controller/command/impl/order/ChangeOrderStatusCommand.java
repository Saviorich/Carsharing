package by.epam.carsharing.controller.command.impl.order;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.service.OrderService;
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

public class ChangeOrderStatusCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeOrderStatusCommand.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage&error=%s&validation=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderStatus status = OrderStatus.valueOf(request.getParameter(RequestParameter.STATUS).toUpperCase());
        int orderId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        String rejectionComment = request.getParameter(RequestParameter.REJECTION_COMMENT);
        logger.log(Level.DEBUG, rejectionComment);

        OrderService orderService = serviceFactory.getOrderService();
        try {
            orderService.changeStatus(orderId, status);
            if (rejectionComment != null) {
                orderService.addRejectionComment(orderId, rejectionComment);
            }
            response.sendRedirect(String.format(GO_TO_ORDERS_PAGE, null, null));
        } catch (ServiceException e) {
            logger.log(Level.FATAL, e);
            response.sendRedirect(String.format(GO_TO_ORDERS_PAGE, true, null));
        }
    }
}
