package by.epam.carsharing.model.dao.impl;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.connection.exception.ConnectionPoolException;
import by.epam.carsharing.model.dao.CarCommentDao;
import by.epam.carsharing.model.dao.Dao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Role;
import by.epam.carsharing.model.entity.car.*;
import by.epam.carsharing.model.entity.user.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarCommentDaoImpl implements CarCommentDao {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String ADD_QUERY = "INSERT INTO car_comment (user_id, car_id, content) VALUE (?, ?, ?);";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM car_comment WHERE id=?;";
    private static final String GET_ALL_BY_CAR_QUERY = "SELECT * FROM car_comment " +
            "JOIN users on users.id = car_comment.user_id " +
            "JOIN cars on cars.id = car_comment.car_id " +
            " WHERE car_id=? " +
            " ORDER BY car_comment.id DESC;";
    private static final String GET_ALL_QUERY = "SELECT * FROM car_comment " +
            "JOIN users on users.id = car_comment.user_id " +
            "JOIN cars on cars.id = car_comment.car_id " +
            "ORDER BY car_comment.id DESC;";

    private static final Role DEFAULT_USER_ROLE = Role.USER;

    @Override
    public List<CarComment> getAllByCarId(int carId) throws DaoException {
        List<CarComment> comments = new ArrayList<>();
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_CAR_QUERY);
        ) {
            statement.setInt(1, carId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String content = resultSet.getString(4);

                // User
                int userId = resultSet.getInt(5);
                String email = resultSet.getString(6);
                String password = resultSet.getString(7);

                User user = new User(userId, email, password, DEFAULT_USER_ROLE);

                // Cars
                String brand = resultSet.getString(10);
                String model = resultSet.getString(11);
                CarColor color = CarColor.valueOf(resultSet.getString(12).toUpperCase());
                int mileage = resultSet.getInt(13);
                GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(14).toUpperCase());
                String year = resultSet.getString(15);
                EngineType engineType = EngineType.valueOf(resultSet.getString(16).toUpperCase());
                BigDecimal pricePerDay = resultSet.getBigDecimal(17);
                String vin = resultSet.getString(18);
                String plate = resultSet.getString(19);
                CarClass carClass = CarClass.valueOf(resultSet.getString(20).toUpperCase());
                String imagePath = resultSet.getString(21);

                Car car = new Car(carId, brand, model, color, mileage, gearboxType, year,
                        engineType, pricePerDay, vin, plate, carClass, imagePath);

                CarComment comment = new CarComment(id, user, car, content);
                comments.add(comment);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return comments;
    }

    @Override
    public Optional<CarComment> getById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<CarComment> getAll() throws DaoException {
        List<CarComment> comments = new ArrayList<>();

        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String content = resultSet.getString(4);

                // User
                int userId = resultSet.getInt(5);
                String email = resultSet.getString(6);
                String password = resultSet.getString(7);

                User user = new User(userId, email, password, DEFAULT_USER_ROLE);

                // Cars
                int carId = resultSet.getInt(9);
                String brand = resultSet.getString(10);
                String model = resultSet.getString(11);
                CarColor color = CarColor.valueOf(resultSet.getString(12).toUpperCase());
                int mileage = resultSet.getInt(13);
                GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(14).toUpperCase());
                String year = resultSet.getString(15);
                EngineType engineType = EngineType.valueOf(resultSet.getString(16).toUpperCase());
                BigDecimal pricePerDay = resultSet.getBigDecimal(17);
                String vin = resultSet.getString(18);
                String plate = resultSet.getString(19);
                CarClass carClass = CarClass.valueOf(resultSet.getString(20).toUpperCase());
                String imagePath = resultSet.getString(21);

                Car car = new Car(carId, brand, model, color, mileage, gearboxType, year,
                        engineType, pricePerDay, vin, plate, carClass, imagePath);

                CarComment comment = new CarComment(id, user, car, content);
                comments.add(comment);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }

        return comments;
    }

    @Override
    public void add(CarComment entity) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_QUERY);
        ) {
            statement.setInt(1, entity.getUser().getId());
            statement.setInt(2, entity.getCar().getId());
            statement.setString(3, entity.getContent());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY);
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

//    public static void main(String[] args) throws ConnectionPoolException, DaoException {
//        ConnectionPool.getInstance().initPoolData();
//        User user = new UserDaoImpl().getById(5).get();
//        Car car = new CarDaoImpl().getById(3).get();
//        CarComment comment = new CarComment(user, car, "l");
//
//        CarCommentDaoImpl dao =  new CarCommentDaoImpl();
//        dao.add(comment);
//        user = new UserDaoImpl().getById(4).get();
//        car = new CarDaoImpl().getById(3).get();
//        comment = new CarComment(user, car, "l");
//        dao.add(comment);
//
//        dao.getAllByCarId(3).stream().forEach(carComment -> System.out.println(carComment));
//    }
}
