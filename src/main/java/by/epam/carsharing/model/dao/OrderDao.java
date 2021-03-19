package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Order;

import java.util.List;

public interface OrderDao extends Dao<Order>{
    List<Order> getAll() throws DaoException;
}
