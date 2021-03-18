package by.epam.carsharing.dao.impl;

import by.epam.carsharing.connection.ConnectionPool;
import by.epam.carsharing.dao.CarDao;
import by.epam.carsharing.entity.car.Car;
import by.epam.carsharing.entity.car.CarClass;
import by.epam.carsharing.entity.car.EngineType;
import by.epam.carsharing.entity.car.GearboxType;
import by.epam.carsharing.connection.exception.ConnectionPoolException;
import by.epam.carsharing.dao.exception.DaoException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM cars WHERE id=?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM cars;";
    private static final String GET_BY_BRAND = "SELECT * FROM cars WHERE LOWER(brand)=?;";
    private static final String GET_BY_YEAR = "SELECT * FROM cars WHERE manufactured_year=?;";
    private static final String GET_BY_CLASS = "SELECT * FROM cars WHERE class=?;";


    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public Optional<Car> getById(int id) throws DaoException {
        Optional<Car> car = Optional.empty();

        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String brand = resultSet.getString(2);
                String model = resultSet.getString(3);
                String color = resultSet.getString(4);
                int mileage = resultSet.getInt(5);
                GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(6).toUpperCase());
                String year = resultSet.getString(7);
                EngineType engineType = EngineType.valueOf(resultSet.getString(8).toUpperCase());
                BigDecimal pricePerDay = resultSet.getBigDecimal(9);
                String vin = resultSet.getString(10);
                String plate = resultSet.getString(11);
                CarClass carClass = CarClass.valueOf(resultSet.getString(12).toUpperCase());
                String imagePath = resultSet.getString(13);

                car = Optional.of(new Car(id, brand, model, color, mileage, gearboxType, year,
                        engineType, pricePerDay, vin, plate, carClass, imagePath));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in getById", e);
        }

        return car;
    }

    @Override
    public List<Car> getAll() throws DaoException {
        List<Car> cars = new ArrayList<>();

        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String brand = resultSet.getString(2);
                String model = resultSet.getString(3);
                String color = resultSet.getString(4);
                int mileage = resultSet.getInt(5);
                GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(6).toUpperCase());
                String year = resultSet.getString(7);
                EngineType engineType = EngineType.valueOf(resultSet.getString(8).toUpperCase());
                BigDecimal pricePerDay = resultSet.getBigDecimal(9);
                String vin = resultSet.getString(10);
                String plate = resultSet.getString(11);
                CarClass carClass = CarClass.valueOf(resultSet.getString(12).toUpperCase());
                String imagePath = resultSet.getString(13);

                Car car = new Car(id, brand, model, color, mileage, gearboxType, year,
                        engineType, pricePerDay, vin, plate, carClass, imagePath);

                cars.add(car);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in getAll", e);
        }
        return cars;
    }

    @Override
    public void add(Car entity) throws DaoException {
    }

    @Override
    public void deleteById(int id) throws DaoException {

    }

    @Override
    public List<Car> getCarsByBrand(String brand) throws DaoException {
        List<Car> cars = new ArrayList<>();

        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_BRAND)
        ) {
            statement.setString(1, brand.toLowerCase());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String model = resultSet.getString(3);
                String color = resultSet.getString(4);
                int mileage = resultSet.getInt(5);
                GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(6).toUpperCase());
                String year = resultSet.getString(7);
                EngineType engineType = EngineType.valueOf(resultSet.getString(8).toUpperCase());
                BigDecimal pricePerDay = resultSet.getBigDecimal(9);
                String vin = resultSet.getString(10);
                String plate = resultSet.getString(11);
                CarClass carClass = CarClass.valueOf(resultSet.getString(12).toUpperCase());
                String imagePath = resultSet.getString(13);

                Car car = new Car(id, brand, model, color, mileage, gearboxType, year,
                        engineType, pricePerDay, vin, plate, carClass, imagePath);

                cars.add(car);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in getCarsByBrand", e);
        }

        return cars;
    }

    @Override
    public List<Car> getCarsByYear(String year) throws DaoException {
        List<Car> cars = new ArrayList<>();

        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_YEAR)
        ) {
            statement.setString(1, year.toLowerCase());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String brand = resultSet.getString(2);
                String model = resultSet.getString(3);
                String color = resultSet.getString(4);
                int mileage = resultSet.getInt(5);
                GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(6).toUpperCase());
                EngineType engineType = EngineType.valueOf(resultSet.getString(8).toUpperCase());
                BigDecimal pricePerDay = resultSet.getBigDecimal(9);
                String vin = resultSet.getString(10);
                String plate = resultSet.getString(11);
                CarClass carClass = CarClass.valueOf(resultSet.getString(12).toUpperCase());
                String imagePath = resultSet.getString(13);

                Car car = new Car(id, brand, model, color, mileage, gearboxType, year,
                        engineType, pricePerDay, vin, plate, carClass, imagePath);

                cars.add(car);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in getCarsByBrand", e);
        }

        return cars;
    }

    @Override
    public List<Car> getCarsByClass(CarClass carClass) throws DaoException {
        List<Car> cars = new ArrayList<>();

        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_CLASS)
        ) {
            statement.setString(1, carClass.toString().toLowerCase());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String brand = resultSet.getString(2);
                String model = resultSet.getString(3);
                String color = resultSet.getString(4);
                int mileage = resultSet.getInt(5);
                GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(6).toUpperCase());
                String year = resultSet.getString(7);
                EngineType engineType = EngineType.valueOf(resultSet.getString(8).toUpperCase());
                BigDecimal pricePerDay = resultSet.getBigDecimal(9);
                String vin = resultSet.getString(10);
                String plate = resultSet.getString(11);
                String imagePath = resultSet.getString(13);

                Car car = new Car(id, brand, model, color, mileage, gearboxType, year,
                        engineType, pricePerDay, vin, plate, carClass, imagePath);

                cars.add(car);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in getCarsByBrand", e);
        }

        return cars;
    }

    @Override
    public void update() throws DaoException {

    }
}
