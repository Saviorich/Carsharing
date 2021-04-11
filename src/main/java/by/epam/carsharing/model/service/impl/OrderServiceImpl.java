package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoHelper;
import by.epam.carsharing.model.dao.OrderDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.service.OrderService;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.validation.OrderValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = DaoHelper.getInstance().getOrderDao();

    @Override
    public Optional<Order> getById(int id) throws ServiceException {
        Optional<Order> order;
        try {
            order = orderDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return order;
    }

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

    @Override
    public void changeStatus(int orderId, OrderStatus status) throws ServiceException {
        try {
            orderDao.changeStatus(orderId, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addRejectionComment(int orderId, String rejectionComment) throws ServiceException {
        try {
            orderDao.addRejectionComment(orderId, rejectionComment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addReturnComment(int orderId, String returnComment) throws ServiceException {
        try {
            orderDao.addReturnComment(orderId, returnComment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
