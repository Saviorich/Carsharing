package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoFactory;
import by.epam.carsharing.model.dao.OrderDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.service.OrderService;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.validation.impl.OrderValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = DaoFactory.getInstance().getOrderDao();

    @Override
    public List<Order> getAll() throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getAllByUserId(int userId) throws ServiceException {
        List<Order> orders = new ArrayList<>();

        try {
            orders = orderDao.getAllByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return orders;
    }

    @Override
    public void add(Order entity) throws ServiceException, InvalidDataException {
        OrderValidator validator = new OrderValidator();

        int carId = entity.getCar().getId();
        Date startDate = entity.getStartDate();
        Date endDate = entity.getEndDate();

        List<Order> orders = getAll();

        if (!validator.isCarAvailableForDates(orders, carId, startDate, endDate)
                || !validator.isDatesValid(startDate, endDate)
                || validator.isAlreadyMade(orders, entity)) {
            throw new InvalidDataException(validator.getMessage());
        }

        try {
            orderDao.add(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
