package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.CarDao;
import by.epam.carsharing.model.dao.DaoFactory;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.car.CarClass;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.car.EngineType;
import by.epam.carsharing.model.entity.car.GearboxType;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.CarService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {

    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Optional<Car> getById(int id) throws ServiceException {
        CarDao dao = daoFactory.getCarDao();
        Optional<Car> car;
        try {
            car = dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return car;
    }

    @Override
    public List<Car> getAll() throws ServiceException {
        List<Car> cars;
        CarDao carDao = daoFactory.getCarDao();

        try {
            cars = carDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("DaoException in getAll", e);
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByBrand(String brand) throws ServiceException {
        List<Car> cars;
        CarDao carDao = daoFactory.getCarDao();

        try {
            cars = carDao.getCarsByBrand(brand);
        } catch (DaoException e) {
            throw new ServiceException("DaoException in getCarsByBrand", e);
        }

        return cars;
    }

    @Override
    public List<Car> getCarsByYear(String year) throws ServiceException {
        List<Car> cars;
        CarDao carDao = daoFactory.getCarDao();

        try {
            cars = carDao.getCarsByYear(year);
        } catch (DaoException e) {
            throw new ServiceException("DaoException in getCarsByBrand", e);
        }

        return cars;
    }

    @Override
    public List<Car> getCarsByClass(CarClass carClass) throws ServiceException {
        List<Car> cars;
        CarDao carDao = daoFactory.getCarDao();

        try {
            cars = carDao.getCarsByClass(carClass);
        } catch (DaoException e) {
            throw new ServiceException("DaoException in getCarsByBrand", e);
        }

        return cars;
    }

    @Override
    public void update(int id, String brand, String model, String color, int mileage, GearboxType gearbox, String year, EngineType engineType, CarClass carClass, BigDecimal price, String imagePath) throws ServiceException {
        CarDao carDao = daoFactory.getCarDao();

        try {
            carDao.update(id, brand, model, color, mileage, gearbox, year, engineType, carClass, price, imagePath);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
