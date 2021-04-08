package by.epam.carsharing.model.service;

import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.car.CarComment;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CarCommentService {
    List<CarComment> getAll() throws ServiceException;
    List<CarComment> getAllByCarId(int carId) throws ServiceException;

    void add(CarComment entity) throws ServiceException;

    void deleteById(int id) throws ServiceException;
}
