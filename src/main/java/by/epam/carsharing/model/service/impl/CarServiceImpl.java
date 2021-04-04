package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.CarDao;
import by.epam.carsharing.model.dao.DaoHelper;
import by.epam.carsharing.model.entity.car.*;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.validation.impl.CarValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {

    private static final CarValidator VALIDATOR = new CarValidator();
    private static final CarDao carDao = DaoHelper.getInstance().getCarDao();

    @Override
    public Optional<Car> getById(int id) throws ServiceException {
        Optional<Car> car;
        try {
            car = carDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return car;
    }

    @Override
    public List<Car> getAll() throws ServiceException {
        List<Car> cars;

        try {
            cars = carDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByName(String criteria) throws ServiceException {
        List<Car> cars;

        try {
            cars = carDao.getCarsByName(criteria);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return cars;
    }

    @Override
    public List<Car> getCarsByYear(String year) throws ServiceException {
        List<Car> cars;

        try {
            cars = carDao.getCarsByYear(year);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return cars;
    }

    @Override
    public List<Car> getCarsByClass(CarClass carClass) throws ServiceException {
        List<Car> cars;

        try {
            cars = carDao.getCarsByClass(carClass);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return cars;
    }

    @Override
    public void update(int id, String brand, String model, CarColor color, int mileage, GearboxType gearbox,
                       String year, EngineType engineType, CarClass carClass, BigDecimal price, String vin, String plate, String imagePath) throws ServiceException, InvalidDataException {

        if (!VALIDATOR.isMileageValid(mileage)
                || !VALIDATOR.isPlateValid(plate)
                || !VALIDATOR.isPriceValid(price)
                || !VALIDATOR.isVinValid(vin)
                || !VALIDATOR.isYearValid(year)) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            carDao.update(id, brand, model, color, mileage, gearbox, year, engineType, carClass, price, imagePath);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(Car car) throws ServiceException, InvalidDataException {

        int mileage = car.getMileage();
        String plate = car.getPlate();
        BigDecimal price = car.getPricePerDay();
        String vin = car.getVin();
        String year = car.getManufacturedYear();

        if (!VALIDATOR.isMileageValid(mileage)
                || !VALIDATOR.isPlateValid(plate)
                || !VALIDATOR.isPriceValid(price)
                || !VALIDATOR.isVinValid(vin)
                || !VALIDATOR.isYearValid(year)) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }
        
        try {
            carDao.add(car);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {

        try {
            carDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
