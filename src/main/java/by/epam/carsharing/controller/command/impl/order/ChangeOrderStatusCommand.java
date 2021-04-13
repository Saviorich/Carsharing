package by.epam.carsharing.controller.command.impl.order;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.service.OrderService;
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

public class ChangeOrderStatusCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeOrderStatusCommand.class);
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage&error=%s&validation=%s";
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = request.getHeader(REFERER);

        OrderStatus status = OrderStatus.valueOf(request.getParameter(RequestParameter.STATUS).toUpperCase());
        int orderId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        String rejectionComment = request.getParameter(RequestParameter.REJECTION_COMMENT);
        String returnComment = request.getParameter(RequestParameter.RETURN_COMMENT);

        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        try {
            orderService.changeStatus(orderId, status);
            if (rejectionComment != null) {
                orderService.addRejectionComment(orderId, rejectionComment);
            }
            if (returnComment != null) {
                orderService.addReturnComment(orderId, returnComment);
            }
        } catch (ServiceException e) {
            logger.log(Level.FATAL, e);
            commandResult = String.format(GO_TO_ORDERS_PAGE, true, null);
        }
        response.sendRedirect(commandResult);
    }
}
