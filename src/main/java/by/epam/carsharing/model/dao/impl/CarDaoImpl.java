package by.epam.carsharing.model.dao.impl;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.dao.CarDao;
import by.epam.carsharing.model.entity.car.*;
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
    private static final String GET_BY_NAME = "SELECT * FROM cars WHERE CONCAT(brand, ' ', model) LIKE CONCAT('%', ?, '%')";
    private static final String GET_BY_YEAR = "SELECT * FROM cars WHERE manufactured_year=?;";
    private static final String GET_BY_CLASS = "SELECT * FROM cars WHERE class=?;";
    private static final String UPDATE_CAR_QUERY = "UPDATE cars SET brand=?, model=?, color=?, mileage=?, gearbox=?, manufactured_year=?, engine_type=?, class=?, price_per_day=?, image_path=? WHERE id=?;";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM cars WHERE id=?;";
    private static final String ADD_CAR_QUERY = "INSERT INTO cars (brand, model, color, mileage, gearbox, manufactured_year, engine_type, price_per_day, vin, plate, class, image_path) " +
            "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public Optional<Car> getById(int id) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            return executeForSingleResult(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> getAll() throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY)
        ) {
            return executeForManyResults(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void add(Car entity) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_CAR_QUERY);
        ){
            statement.setString(1, entity.getBrand());
            statement.setString(2, entity.getModel());
            statement.setString(3, entity.getColor().toString());
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
    public List<Car> getCarsByName(String criteria) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_NAME)
        ) {
            statement.setString(1, criteria);
            return executeForManyResults(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> getCarsByYear(String year) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_YEAR)
        ) {
            statement.setString(1, year.toLowerCase());
            return executeForManyResults(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> getCarsByClass(CarClass carClass) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_CLASS)
        ) {
            statement.setString(1, carClass.toString().toLowerCase());
            return executeForManyResults(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Car entity) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_QUERY)
        ) {
            statement.setString(1, entity.getBrand());
            statement.setString(2, entity.getModel());
            statement.setString(3, entity.getColor().toString().toLowerCase());
            statement.setInt(4, entity.getMileage());
            statement.setString(5, entity.getGearbox().toString().toLowerCase());
            statement.setString(6, entity.getManufacturedYear());
            statement.setString(7, entity.getEngineType().toString().toLowerCase());
            statement.setString(8, entity.getCarClass().toString().toLowerCase());
            statement.setBigDecimal(9, entity.getPricePerDay());
            statement.setString(10, entity.getImagePath());
            statement.setInt(11, entity.getId());

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> executeForManyResults(PreparedStatement statement) throws SQLException {
        List<Car> cars = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String brand = resultSet.getString(2);
            String model = resultSet.getString(3);
            CarColor color = CarColor.valueOf(resultSet.getString(4).toUpperCase());
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

        return cars;
    }

    @Override
    public Optional<Car> executeForSingleResult(PreparedStatement statement) throws SQLException {
        Optional<Car> car = Optional.empty();

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String brand = resultSet.getString(2);
            String model = resultSet.getString(3);
            CarColor color = CarColor.valueOf(resultSet.getString(4).toUpperCase());
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

        return car;
    }
}
