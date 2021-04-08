package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.car.CarComment;

import java.util.List;

public interface CarCommentDao extends Dao<CarComment> {
    List<CarComment> getAllByCarId(int carId) throws DaoException;
}
