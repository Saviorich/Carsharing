package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.status.OrderStatus;

import java.util.List;

public interface OrderDao extends Dao<Order>{
    List<Order> getAllByUserId(int userId) throws DaoException;

    void changeStatus(int orderId, OrderStatus status) throws DaoException;

    void addRejectionComment(int orderId, String comment) throws DaoException;
    void addReturnComment(int orderId, String comment) throws DaoException;
}
