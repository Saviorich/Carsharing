package by.epam.carsharing.validation;

import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.Role;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.util.DateUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderValidatorTest {

    private static final OrderValidator VALIDATOR = new OrderValidator();
    private static final List<Order> ORDERS = new ArrayList<>();
    private static final DateUtil DATE_UTILS = new DateUtil();

    static {
        initializeOrders();
    }

    @ParameterizedTest
    @CsvSource({"5,2021-04-11,2021-04-12", "3,2021-04-13,2021-04-13", "1,2021-04-13,2021-04-14"})
    void testIsCarAvailableForDatesShouldReturnTrue(int carId, String startDate, String endDate) throws InvalidDataException {
        Date date1 = DATE_UTILS.parseDate(startDate);
        Date date2 = DATE_UTILS.parseDate(endDate);
        assertTrue(VALIDATOR.isCarAvailableForDates(ORDERS, carId, date1, date2));
    }

    @ParameterizedTest
    @CsvSource({"3,2021-04-11,2021-04-12", "1,2021-04-12,2021-04-12",
            "1,2021-04-11,2021-04-11", "17, 2021-04-11,2021-04-11"})
    void testIsCarAvailableForDatesShouldReturnFalse(int carId, String startDate, String endDate) throws InvalidDataException {
        Date date1 = DATE_UTILS.parseDate(startDate);
        Date date2 = DATE_UTILS.parseDate(endDate);
        assertFalse(VALIDATOR.isCarAvailableForDates(ORDERS, carId, date1, date2));
    }

    @ParameterizedTest
    @CsvSource({"10,6,2021-04-11,2021-04-12", "4,17,2021-04-16,2021-04-26"})
    void testIsAlreadyMadeShouldReturnTrue(int userId, int carId, String startDateString, String endDateString) throws InvalidDataException {
        Order order = createOrder(userId, carId, startDateString, endDateString, OrderStatus.NEW);
        assertTrue(VALIDATOR.isAlreadyMade(ORDERS, order));
    }

    @ParameterizedTest
    @CsvSource({"10,17,2021-04-11,2021-04-12", "4,6,2021-04-16,2021-04-26", "1,5,2021-04-11,2021-04-12"})
    void testIsAlreadyMadeShouldReturnFalse(int userId, int carId, String startDateString, String endDateString) throws InvalidDataException {
        Order order = createOrder(userId, carId, startDateString, endDateString, OrderStatus.NEW);
        assertFalse(VALIDATOR.isAlreadyMade(ORDERS, order));
    }

    @ParameterizedTest
    @CsvSource({"2021-05-11,2021-05-11", "2021-05-11,2021-05-12", "2021-05-11,2021-06-11"})
    void testAreDatesValidShouldReturnTrue(String startDate, String endDate) throws InvalidDataException{
        Date date1 = DATE_UTILS.parseDate(startDate);
        Date date2 = DATE_UTILS.parseDate(endDate);
        assertTrue(VALIDATOR.areDatesValid(date1, date2));
    }

    // Result depends on current date
    @ParameterizedTest
    @CsvSource({"2021-04-10,2021-04-11", "2021-04-11,2021-03-11", "2021-04-15,2021-04-11"})
    void testAreDatesValidShouldReturnFalse(String startDate, String endDate) throws InvalidDataException {
        Date date1 = DATE_UTILS.parseDate(startDate);
        Date date2 = DATE_UTILS.parseDate(endDate);
        assertFalse(VALIDATOR.areDatesValid(date1, date2));
    }

    private static void initializeOrders() {
        Order order;
        try {
            order = createOrder(10, 6, "2021-04-11", "2021-04-12", OrderStatus.NEW);
            ORDERS.add(order);
            order = createOrder(4, 17, "2021-04-16", "2021-04-26", OrderStatus.NEW);
            ORDERS.add(order);
            order = createOrder(1, 3, "2021-04-11", "2021-04-12", OrderStatus.APPROVED);
            ORDERS.add(order);
            order = createOrder(3, 17, "2021-04-11", "2021-04-11", OrderStatus.APPROVED);
            ORDERS.add(order);
            order = createOrder(1, 17, "2021-04-12", "2021-04-13", OrderStatus.APPROVED);
            ORDERS.add(order);
            order = createOrder(5, 1, "2021-04-11", "2021-04-11", OrderStatus.RECEIVED);
            ORDERS.add(order);
            order = createOrder(6, 1, "2021-04-12", "2021-04-12", OrderStatus.PAID);
            ORDERS.add(order);
            order = createOrder(9, 17, "2021-04-11", "2021-04-11", OrderStatus.APPROVED);
            ORDERS.add(order);
        } catch (InvalidDataException e) {
            // Data is always valid
        }
    }

    private static Order createOrder(int userId, int carId, String startDateString, String endDateString, OrderStatus status) throws InvalidDataException {
        User user = new User(userId, null, null, Role.USER);
        Car car = new Car(carId, null, null, null, 0, null, null,
                null, null, null, null, null, null);
        Date startDate = DATE_UTILS.parseDate(startDateString);
        Date endDate = DATE_UTILS.parseDate(endDateString);
        return new Order(1, user, car, status, startDate, endDate);
    }
}