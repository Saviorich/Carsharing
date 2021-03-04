package by.epam.carsharing.dao;

import by.epam.carsharing.entity.car.Car;
import by.epam.carsharing.entity.car.CarClass;

import java.time.Year;
import java.util.List;

public interface CarDao extends Dao<Car> {
    List<Car> getCarsByBrand(String brand);
    List<Car> getCarsByYear(Year year);
    List<Car> getCarsByClass(CarClass carClass);

    void update();
}
