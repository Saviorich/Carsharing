package by.epam.carsharing.validation.impl;

import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.util.DateUtils;
import by.epam.carsharing.validation.Validator;

import java.util.Date;
import java.util.List;

public class OrderValidator extends Validator {

    private static final DateUtils DATE_UTILS = new DateUtils();

    public boolean isCarAvailableForDates(List<Order> orders, int carId, Date startDate, Date endDate) {
        boolean isAvailable =  orders.stream()
                .filter(order -> order.getCar().getId() == carId && order.getStatus() == OrderStatus.APPROVED)
                .noneMatch(order -> DATE_UTILS.isBetweenDates(startDate, order.getStartDate(), order.getEndDate())
                            && DATE_UTILS.isBetweenDates(endDate, order.getStartDate(), order.getEndDate()));
        if (!isAvailable) {
            message = DATE_UTILS.formatDate(startDate) + " : " + DATE_UTILS.formatDate(endDate);
        }
        return isAvailable;
    }

    public boolean isAlreadyMade(List<Order>orders, Order orderToCheck) {
        boolean isMade = orders.stream()
                .filter(order -> order.getUser().getId() == orderToCheck.getUser().getId())
                .noneMatch(order -> order.getStartDate() == orderToCheck.getStartDate()
                        && order.getEndDate() == orderToCheck.getEndDate());
        if (isMade) {
            message = "Order is already made";
        }
        return isMade;
    }

    public boolean isDatesValid(Date startDate, Date endDate) {
        if (startDate.before(DATE_UTILS.getCurrentDateWithoutTime())) {
            message = "Start date is earlier than today's date";
            return false;
        } else if (startDate.after(endDate)) {
            message = "Start date is earlier than end date";
            return false;
        }
        return true;
    }

}
