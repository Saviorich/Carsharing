package by.epam.carsharing.validation.impl;

import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.util.DateUtil;
import by.epam.carsharing.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class OrderValidator extends Validator {

    private static final Logger logger = LogManager.getLogger(OrderValidator.class);

    private static final DateUtil DATE_UTILS = new DateUtil();

    public boolean isCarAvailableForDates(List<Order> orders, int carId, Date startDate, Date endDate) {
        boolean isAvailable =  orders.stream()
                .filter(order -> order.getCar().getId() == carId
                        && (order.getStatus() == OrderStatus.APPROVED
                        || order.getStatus() == OrderStatus.PAID
                        || order.getStatus() == OrderStatus.RECEIVED))
                .noneMatch(order -> DATE_UTILS.isBetweenDates(startDate, order.getStartDate(), order.getEndDate())
                            && DATE_UTILS.isBetweenDates(endDate, order.getStartDate(), order.getEndDate()));
        if (!isAvailable) {
            message = DATE_UTILS.formatDate(startDate) + " : " + DATE_UTILS.formatDate(endDate);
        }
        return isAvailable;
    }

    public boolean isAlreadyMade(List<Order>orders, Order orderToCheck) {
        boolean isMade = orders.stream()
                .filter(order -> order.getUser().getId() == orderToCheck.getUser().getId()
                        && order.getCar().getId() == orderToCheck.getCar().getId() && order.getStatus() == OrderStatus.NEW)
                .anyMatch(order -> order.getStartDate().equals(orderToCheck.getStartDate())
                        && order.getEndDate().equals(orderToCheck.getEndDate()));
        if (isMade) {
            message = "Order is already made";
        }
        return isMade;
    }

    public boolean isDatesValid(Date startDate, Date endDate) {
        if (startDate.before(DATE_UTILS.getCurrentDateWithoutTime())) {
            message = "Start date is earlier than todays date";
            return false;
        } else if (startDate.after(endDate)) {
            message = "Start date is earlier than end date";
            return false;
        }
        return true;
    }

}
