package by.epam.carsharing.dao;

import by.epam.carsharing.entity.car.Car;
import by.epam.carsharing.entity.car.CarClass;
import by.epam.carsharing.exception.DaoException;

import java.time.Year;
import java.util.List;

public interface CarDao extends Dao<Car> {
    List<Car> getCarsByBrand(String brand) throws DaoException;
    List<Car> getCarsByYear(String year) throws DaoException;
    List<Car> getCarsByClass(CarClass carClass) throws DaoException;

    void update() throws DaoException;
}
