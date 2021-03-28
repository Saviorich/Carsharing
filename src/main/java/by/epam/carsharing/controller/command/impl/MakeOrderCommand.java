package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.OrderService;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import by.epam.carsharing.validation.impl.OrderValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeOrderCommand implements Command {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(MakeOrderCommand.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage";
    private static final String GO_TO_ORDER_PAGE = "Controller?command=gotoorderpage&data_id=%d&error=%s";
    private static final String INVALID_DATE_MESSAGE =  "Invalid date";
    private static final String SERVICE_EXCEPTION_MESSAGE = "Something went wrong";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderValidator validator = new OrderValidator();
        int carId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
        OrderService orderService = serviceFactory.getOrderService();
        CarService carService = serviceFactory.getCarService();
        User user = ((User) request.getSession().getAttribute(SessionAttribute.USER));

        try {
            Date startDate = parseDate(request.getParameter(RequestParameter.START_DATE));
            Date endDate = parseDate(request.getParameter(RequestParameter.END_DATE));
            if (!validator.isCarAvailableForDates(orderService.getAll(), carId, startDate, endDate)
                    || !validator.isDatesValid(startDate, endDate)) {
                response.sendRedirect(String.format(GO_TO_ORDER_PAGE, carId, validator.getMessage()));
            } else {
                Car car = carService.getById(carId).get();
                Order order = new Order(user, car, OrderStatus.NEW, startDate, endDate, "", "");
                orderService.add(order);
                response.sendRedirect(GO_TO_ORDERS_PAGE);
            }
        } catch (ServiceException e){
            logger.log(Level.ERROR, e);
            response.sendRedirect(String.format(GO_TO_ORDER_PAGE, carId, SERVICE_EXCEPTION_MESSAGE));
        }
    }

    private Date parseDate(String stringDate) throws ServiceException {
        try {
            return DATE_FORMAT.parse(stringDate);
        } catch (ParseException e) {
            throw new ServiceException(INVALID_DATE_MESSAGE, e);
        }
    }
}
