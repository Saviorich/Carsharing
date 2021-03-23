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
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = serviceFactory.getOrderService();
        PassportService passportService = serviceFactory.getPassportService();


        Date startDate = null;
        Date endDate = null;
        Date issueDate = null;
        try {
            startDate = sdf.parse(request.getParameter(RequestParameter.START_DATE));
            endDate = sdf.parse(request.getParameter(RequestParameter.END_DATE));
            issueDate = sdf.parse(request.getParameter(RequestParameter.ISSUE_DATE));
        } catch (ParseException e) {
            logger.log(Level.DEBUG, e);
        }

        int userId = ((User) request.getSession().getAttribute(SessionAttribute.USER)).getId();
        int carId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
        String passportNumber = request.getParameter(RequestParameter.PASSPORT_NUMBER);
        String identificationNumber = request.getParameter(RequestParameter.IDENTIFICATION_NUMBER);

        try {
            Passport passport = new Passport(passportNumber, identificationNumber, issueDate);
            passportService.add(passport);
            Order order = new Order(userId, carId, 0, startDate, endDate, "", "",
                    passport.getPassportNumber());
            orderService.add(order);
            response.sendRedirect(GO_TO_ORDERS_PAGE);
        } catch (ServiceException e){
            logger.log(Level.ERROR, e);
        }
    }
}
