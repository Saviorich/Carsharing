package by.epam.carsharing.service;

import by.epam.carsharing.entity.car.Car;
import by.epam.carsharing.entity.car.CarClass;
import by.epam.carsharing.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> getById(int id) throws ServiceException;
    List<Car> getAll() throws ServiceException;

    List<Car> getCarsByBrand(String brand) throws ServiceException;
    List<Car> getCarsByYear(String year) throws ServiceException;
    List<Car> getCarsByClass(CarClass carClass) throws ServiceException;
}
