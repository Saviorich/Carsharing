package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.Role;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.OrderService;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToUsersOrdersPage implements Command {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(GoToUsersOrdersPage.class);
    private static final String ORDERS_PAGE = "/WEB-INF/jsp/user_orders.jsp";
    private static final String LOGIN_PAGE = "/login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders;
        RequestDispatcher requestDispatcher;
        OrderService orderService = serviceFactory.getOrderService();
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);

        try {
            if (user != null) {
                requestDispatcher = request.getRequestDispatcher(ORDERS_PAGE);
                orders = user.getRole() == Role.ADMIN
                        ? orderService.getAll()
                        : orderService.getAllByUserId(user.getId());
                request.setAttribute(RequestParameter.ORDERS, orders);
            } else {
                requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            }
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
