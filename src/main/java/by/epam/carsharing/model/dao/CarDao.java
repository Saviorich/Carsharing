package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.car.CarClass;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.car.EngineType;
import by.epam.carsharing.model.entity.car.GearboxType;

import java.math.BigDecimal;
import java.util.List;

public interface CarDao extends Dao<Car> {
    List<Car> getCarsByBrand(String brand) throws DaoException;
    List<Car> getCarsByYear(String year) throws DaoException;
    List<Car> getCarsByClass(CarClass carClass) throws DaoException;

    void update(int id, String brand, String model, String color, int mileage, GearboxType gearbox, String year, EngineType engineType, CarClass carClass, BigDecimal price, String imagePath) throws DaoException;
}
