package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> getAll() throws ServiceException;
}
