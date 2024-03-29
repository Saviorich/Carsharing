package by.epam.carsharing.model.service;

import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.car.CarComment;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CarCommentService {
    List<CarComment> getAll() throws ServiceException;
    List<CarComment> getAllByCarId(int carId) throws ServiceException;

    void add(CarComment entity) throws ServiceException, InvalidDataException;

    void deleteById(int id) throws ServiceException;

    int getDataAmount(int carId) throws ServiceException;
    List<CarComment> getCommentsForPage(int carId, int recordsPerPage, int currentPage) throws ServiceException;
}
