package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.user.Passport;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.OrderService;
import by.epam.carsharing.model.service.PassportService;
import by.epam.carsharing.model.service.ServiceFactory;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeOrderCommand implements Command {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(MakeOrderCommand.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = serviceFactory.getOrderService();

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = DATE_FORMAT.parse(request.getParameter(RequestParameter.START_DATE));
            endDate = DATE_FORMAT.parse(request.getParameter(RequestParameter.END_DATE));
        } catch (ParseException e) {
            logger.log(Level.DEBUG, e);
            // TODO: show error message on page
        }

        int userId = ((User) request.getSession().getAttribute(SessionAttribute.USER)).getId();
        int carId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        try {
            Order order = new Order(userId, carId, 0, startDate, endDate, "", "");
            orderService.add(order);
            response.sendRedirect(GO_TO_ORDERS_PAGE);
        } catch (ServiceException e){
            logger.log(Level.ERROR, e);
        }
    }
}
