package by.epam.carsharing.model.dao.impl;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.connection.exception.ConnectionPoolException;
import by.epam.carsharing.model.dao.DaoHelper;
import by.epam.carsharing.model.dao.OrderDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Order;
import by.epam.carsharing.model.entity.Role;
import by.epam.carsharing.model.entity.car.*;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.entity.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String GET_BY_ID_QUERY = "SELECT * FROM orders" +
            "    INNER JOIN status on status.id = orders.status_id" +
            "    INNER JOIN users on orders.user_id = users.id" +
            "    INNER JOIN role on users.role = role.id" +
            "    INNER JOIN cars on orders.car_id = cars.id" +
            "    WHERE orders.id=?";
    private static final String GET_ALL_BY_USER_ID_QUERY = "SELECT * FROM orders" +
            "    INNER JOIN status on status.id = orders.status_id" +
            "    INNER JOIN users on orders.user_id = users.id" +
            "    INNER JOIN role on users.role = role.id" +
            "    INNER JOIN cars on orders.car_id = cars.id" +
            "    WHERE user_id=?;";
    private static final String GET_ALL_ORDERS_QUERY = "SELECT * FROM orders" +
            "    INNER JOIN status on status.id = orders.status_id" +
            "    INNER JOIN users on orders.user_id = users.id" +
            "    INNER JOIN role on users.role = role.id" +
            "    INNER JOIN cars on orders.car_id = cars.id";
    private static final String ADD_ORDER_QUERY = "INSERT INTO orders " +
            "(user_id, car_id, status_id, start_date, end_date, rejection_comment, return_comment) " +
            "VALUE (?, ?, ?, ?, ?, ?, ?);";
    private static final String CHANGE_STATUS_QUERY =
            "UPDATE orders " +
            "SET status_id = (SELECT id FROM status WHERE status_name=? AND status_group='orders') "+
            "WHERE orders.id = ?;";
    private static final String ADD_REJECTION_QUERY =
            "UPDATE orders " +
            "SET rejection_comment=? " +
            "WHERE id=?";
    private static final String ADD_RETURN_QUERY =
            "UPDATE orders " +
            "SET return_comment=? " +
            "WHERE id=?";

    private static final int DEFAULT_ORDER_STATUS_ID = 1;

    @Override
    public Optional<Order> getById(int id) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY);
        ) {
            statement.setInt(1, id);
            return executeForSingleResult(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void add(Order entity) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_ORDER_QUERY)
        ) {
            statement.setInt(1, entity.getUser().getId());
            statement.setInt(2, entity.getCar().getId());
            statement.setInt(3, DEFAULT_ORDER_STATUS_ID);
            statement.setDate(4, new java.sql.Date(entity.getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(entity.getEndDate().getTime()));
            statement.setString(6, entity.getRejectionComment());
            statement.setString(7, entity.getReturnComment());

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getAll() throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS_QUERY)
        ) {
            return executeForManyResults(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> getAllByUserId(int userId) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_USER_ID_QUERY)
        ) {
            statement.setInt(1, userId);
            return executeForManyResults(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void changeStatus(int orderId, OrderStatus status) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS_QUERY)
        ) {
            statement.setString(1, status.toString().toLowerCase());
            statement.setInt(2, orderId);

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addRejectionComment(int orderId, String comment) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_REJECTION_QUERY)
        ) {
            statement.setString(1, comment);
            statement.setInt(2, orderId);

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addReturnComment(int orderId, String comment) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_RETURN_QUERY)
        ) {
            statement.setString(1, comment);
            statement.setInt(2, orderId);

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> executeForManyResults(PreparedStatement statement) throws SQLException {
        List<Order> orders = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Order
            int id = resultSet.getInt(1);
            OrderStatus status = OrderStatus.valueOf(resultSet.getString(11).toUpperCase());
            Date startDate = resultSet.getDate(5);
            Date endDate = resultSet.getDate(6);
            String rejectionComment = resultSet.getString(7);
            String returnComment = resultSet.getString(8);

            // User
            int userId = resultSet.getInt(12);
            String email = resultSet.getString(13);
            String password = resultSet.getString(14);
            Role role = Role.valueOf(resultSet.getString(17).toUpperCase());
            User user = new User(userId, email, password, role);

            // Car
            int carId = resultSet.getInt(18);
            String brand = resultSet.getString(19);
            String model = resultSet.getString(20);
            CarColor color = CarColor.valueOf(resultSet.getString(21).toUpperCase());
            int mileage = resultSet.getInt(22);
            GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(23).toUpperCase());
            String manufacturedYear = resultSet.getString(24);
            EngineType engineType = EngineType.valueOf(resultSet.getString(25).toUpperCase());
            BigDecimal pricePerDay = resultSet.getBigDecimal(26);
            String vin = resultSet.getString(27);
            String plate = resultSet.getString(28);
            CarClass carClass = CarClass.valueOf(resultSet.getString(29).toUpperCase());
            String imagePath = resultSet.getString(30);
            Car car = new Car(carId, brand, model, color, mileage, gearboxType, manufacturedYear, engineType, pricePerDay,
                    vin, plate, carClass, imagePath);

            Order order = new Order(id, user, car, status, startDate, endDate, rejectionComment, returnComment);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Optional<Order> executeForSingleResult(PreparedStatement statement) throws SQLException {
        Optional<Order> order = Optional.empty();

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Order
            int id = resultSet.getInt(1);
            OrderStatus status = OrderStatus.valueOf(resultSet.getString(11).toUpperCase());
            Date startDate = resultSet.getDate(5);
            Date endDate = resultSet.getDate(6);
            String rejectionComment = resultSet.getString(7);
            String returnComment = resultSet.getString(8);

            // User
            int userId = resultSet.getInt(12);
            String email = resultSet.getString(13);
            String password = resultSet.getString(14);
            Role role = Role.valueOf(resultSet.getString(17).toUpperCase());
            User user = new User(userId, email, password, role);

            // Car
            int carId = resultSet.getInt(18);
            String brand = resultSet.getString(19);
            String model = resultSet.getString(20);
            CarColor color = CarColor.valueOf(resultSet.getString(21).toUpperCase());
            int mileage = resultSet.getInt(22);
            GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(23).toUpperCase());
            String manufacturedYear = resultSet.getString(24);
            EngineType engineType = EngineType.valueOf(resultSet.getString(25).toUpperCase());
            BigDecimal pricePerDay = resultSet.getBigDecimal(26);
            String vin = resultSet.getString(27);
            String plate = resultSet.getString(28);
            CarClass carClass = CarClass.valueOf(resultSet.getString(29).toUpperCase());
            String imagePath = resultSet.getString(30);
            Car car = new Car(carId, brand, model, color, mileage, gearboxType, manufacturedYear, engineType, pricePerDay,
                    vin, plate, carClass, imagePath);

            order = Optional.of(new Order(id, user, car, status, startDate, endDate, rejectionComment, returnComment));
        }
        return order;
    }
}
