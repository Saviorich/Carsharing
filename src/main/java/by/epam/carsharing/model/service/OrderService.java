package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> getAll() throws ServiceException;
    List<Order> getAllByUserId(int userId) throws ServiceException;
    void add(Order entity) throws ServiceException, InvalidDataException;

    void changeStatus(int orderId, OrderStatus status) throws ServiceException;
}
