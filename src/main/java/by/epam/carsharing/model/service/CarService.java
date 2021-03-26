package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.car.*;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> getById(int id) throws ServiceException;
    List<Car> getAll() throws ServiceException;

    List<Car> getCarsByBrand(String brand) throws ServiceException;
    List<Car> getCarsByYear(String year) throws ServiceException;
    List<Car> getCarsByClass(CarClass carClass) throws ServiceException;

    void update(int id, String brand, String model, CarColor color, int mileage, GearboxType gearbox,
                String year, EngineType engineType, CarClass carClass, BigDecimal price, String imagePath) throws ServiceException;

    void add(Car car) throws ServiceException;

    void deleteById(int id) throws ServiceException;
}
