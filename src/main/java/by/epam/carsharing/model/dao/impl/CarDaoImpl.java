package by.epam.carsharing.model.dao.impl;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.dao.CarDao;
import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.car.CarClass;
import by.epam.carsharing.model.entity.car.EngineType;
import by.epam.carsharing.model.entity.car.GearboxType;
import by.epam.carsharing.model.connection.exception.ConnectionPoolException;
import by.epam.carsharing.model.dao.exception.DaoException;

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
    private static final String UPDATE_CAR_QUERY = "UPDATE cars SET brand=?, model=?, color=?, mileage=?, gearbox=?, manufactured_year=?, engine_type=?, class=?, price_per_day=?, image_path=? WHERE id=?;";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM cars WHERE id=?;";
    private static final String ADD_CAR_QUERY = "INSERT INTO cars (brand, model, color, mileage, gearbox, manufactured_year, engine_type, price_per_day, vin, plate, class, image_path) " +
            "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            throw new DaoException(e);
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
            throw new DaoException(e);
        }
        return cars;
    }

    @Override
    public void add(Car entity) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_CAR_QUERY);
        ){
            statement.setString(1, entity.getBrand());
            statement.setString(2, entity.getModel());
            statement.setString(3, entity.getColor());
            statement.setInt(4, entity.getMileage());
            statement.setString(5, entity.getGearbox().toString().toLowerCase());
            statement.setString(6, entity.getManufacturedYear());
            statement.setString(7, entity.getEngineType().toString().toLowerCase());
            statement.setBigDecimal(8, entity.getPricePerDay());
            statement.setString(9, entity.getVin());
            statement.setString(10, entity.getPlate());
            statement.setString(11, entity.getCarClass().toString().toLowerCase());
            statement.setString(12, entity.getImagePath());

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
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
            throw new DaoException(e);
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
            throw new DaoException(e);
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
            throw new DaoException(e);
        }

        return cars;
    }

    @Override
    public void update(int id, String brand, String model, String color, int mileage, GearboxType gearbox,
                       String year, EngineType engineType, CarClass carClass, BigDecimal price, String imagePath) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_QUERY)
        ) {
            statement.setString(1, brand);
            statement.setString(2, model);
            statement.setString(3, color);
            statement.setInt(4, mileage);
            statement.setString(5, gearbox.toString().toLowerCase());
            statement.setString(6, year);
            statement.setString(7, engineType.toString().toLowerCase());
            statement.setString(8, carClass.toString().toLowerCase());
            statement.setBigDecimal(9, price);
            statement.setString(10, imagePath);
            statement.setInt(11, id);

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }
}
