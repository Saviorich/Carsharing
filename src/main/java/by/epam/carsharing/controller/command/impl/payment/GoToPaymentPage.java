package by.epam.carsharing.controller.command.impl.payment;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.OrderService;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.PriceCalculator;
import by.epam.carsharing.util.RequestParameter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToPaymentPage implements Command {

    private static final Logger logger = LogManager.getLogger(GoToPaymentPage.class);
    private static final String PAYMENT_PAGE  = "/WEB-INF/jsp/payment.jsp";
    private static final String ERROR_PAGE = "error.jsp";
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        int orderId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
        OrderService orderService = serviceFactory.getOrderService();
        CarService carService = serviceFactory.getCarService();
        PriceCalculator priceCalculator = new PriceCalculator();

        try {
            Order order = orderService.getById(orderId).get();

            BigDecimal totalPrice = priceCalculator.calculatePrice(order);
            request.setAttribute(RequestParameter.TOTAL_PRICE, totalPrice);
            request.setAttribute(RequestParameter.DATA, order);
            dispatcher = request.getRequestDispatcher(PAYMENT_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            dispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        dispatcher.forward(request, response);
    }
}
