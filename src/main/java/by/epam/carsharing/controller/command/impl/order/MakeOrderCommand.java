package by.epam.carsharing.controller.command.impl.order;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.OrderService;
import by.epam.carsharing.model.service.ServiceProvider;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.DateUtil;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Create and saves {@link Order} in the database
 * @see Command
 */
public class MakeOrderCommand implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger logger = LogManager.getLogger(MakeOrderCommand.class);
    private static final DateUtil DATE_UTILS = new DateUtil();
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage";
    private static final String GO_TO_ORDER_PAGE = "Controller?command=gotoorderpage&data_id=%d&error=%s&validation=%s";
    private static final String ERROR_MESSAGE = "Something went wrong";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = GO_TO_ORDERS_PAGE;

        int carId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        CarService carService = SERVICE_PROVIDER.getCarService();
        User user = ((User) request.getSession().getAttribute(SessionAttribute.USER));

        try {
            Date startDate = DATE_UTILS.parseDate(request.getParameter(RequestParameter.START_DATE));
            Date endDate = DATE_UTILS.parseDate(request.getParameter(RequestParameter.END_DATE));
            Car car = carService.getById(carId).get();
            Order order = new Order(user, car, OrderStatus.NEW, startDate, endDate, "", "");
            orderService.add(order);
        } catch (ServiceException e){
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_ORDER_PAGE, carId, ERROR_MESSAGE, null);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_ORDER_PAGE, carId, null, e.getMessage());
        }
        response.sendRedirect(commandResult);
    }
}
