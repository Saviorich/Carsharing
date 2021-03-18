package by.epam.carsharing.service.impl;

import by.epam.carsharing.dao.CarDao;
import by.epam.carsharing.dao.DaoFactory;
import by.epam.carsharing.entity.car.Car;
import by.epam.carsharing.entity.car.CarClass;
import by.epam.carsharing.dao.exception.DaoException;
import by.epam.carsharing.service.exception.ServiceException;
import by.epam.carsharing.service.CarService;

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
}
