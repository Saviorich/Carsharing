package by.epam.carsharing.dao.impl;

import by.epam.carsharing.connection.ConnectionPool;
import by.epam.carsharing.dao.CarDao;
import by.epam.carsharing.entity.car.Car;
import by.epam.carsharing.entity.car.CarClass;
import by.epam.carsharing.entity.car.EngineType;
import by.epam.carsharing.entity.car.GearboxType;
import by.epam.carsharing.exception.ConnectionPoolException;
import by.epam.carsharing.exception.DaoException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM cars WHERE id=?;";


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
                Year year = Year.parse(resultSet.getString(7));
                EngineType engineType = EngineType.valueOf(resultSet.getString(8).toUpperCase());
                BigDecimal pricePerDay = resultSet.getBigDecimal(9);
                String vin = resultSet.getString(10);
                String plate = resultSet.getString(11);
                CarClass carClass = CarClass.valueOf(resultSet.getString(12).toUpperCase());
                String imagePath = resultSet.getString(13);

                car = Optional.of(new Car(
                        id, brand, model, color, mileage, gearboxType, year, engineType, pricePerDay, vin, plate, carClass, imagePath
                ));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in getById", e);
        }

        return car;
    }

    @Override
    public List<Car> getAll() throws DaoException {
        return null;
    }

    @Override
    public void save(Car entity) throws DaoException {

    }

    @Override
    public void deleteById(int id) throws DaoException {

    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        return null;
    }

    @Override
    public List<Car> getCarsByYear(Year year) {
        return null;
    }

    @Override
    public List<Car> getCarsByClass(CarClass carClass) {
        return null;
    }

    @Override
    public void update() {

    }
}
