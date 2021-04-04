package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.entity.car.*;
import by.epam.carsharing.model.dao.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;

public interface CarDao extends Dao<Car> {
    List<Car> getCarsByName(String criteria) throws DaoException;
    List<Car> getCarsByYear(String year) throws DaoException;
    List<Car> getCarsByClass(CarClass carClass) throws DaoException;

    void update(int id, String brand, String model, CarColor color, int mileage, GearboxType gearbox, String year, EngineType engineType, CarClass carClass, BigDecimal price, String imagePath) throws DaoException;
}
