package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoFactory;
import by.epam.carsharing.model.dao.OrderDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.service.OrderService;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = DaoFactory.getInstance().getOrderDao();

    @Override
    public List<Order> getAll() throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.getAll();
        } catch (DaoException e){
            throw new ServiceException(e);
        }
        return orders;
    }
}