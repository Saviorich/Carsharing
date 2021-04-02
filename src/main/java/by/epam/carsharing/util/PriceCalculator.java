package by.epam.carsharing.util;

import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.exception.InvalidDataException;

import java.math.BigDecimal;
import java.util.Date;

public class PriceCalculator {

    private static final DateUtil DATE_UTILS = new DateUtil();

    public BigDecimal calculatePrice(Order order) {
        BigDecimal carPricePerDay = order.getCar().getPricePerDay();

        Date startDate = order.getStartDate();
        Date endDate = order.getEndDate();

        BigDecimal daysInTotal = new BigDecimal(DATE_UTILS.calculateDaysBetweenDates(startDate, endDate) + 1);
        return daysInTotal.multiply(carPricePerDay);
    }
}