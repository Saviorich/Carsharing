package by.epam.carsharing.model.dao.impl;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.connection.exception.ConnectionPoolException;
import by.epam.carsharing.model.dao.OrderDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Order;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String GET_ALL_ORDERS_QUERY = "SELECT * FROM orders;";
    private static final String ADD_ORDER_QUERY = "INSERT INTO orders " +
            "(user_id, car_id, status_id, start_date, end_date, rejection_comment, return_comment, passport_number) " +
            "VALUE (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final int DEFAULT_ORDER_STATUS_ID = 1;

    @Override
    public Optional<Order> getById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void add(Order entity) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_ORDER_QUERY)
        ) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getCarId());
            statement.setInt(3, DEFAULT_ORDER_STATUS_ID);
            statement.setDate(4, new java.sql.Date(entity.getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(entity.getEndDate().getTime()));
            statement.setString(6, entity.getRejectionComment());
            statement.setString(7, entity.getReturnComment());
            logger.log(Level.DEBUG, entity.getPassportNumber());
            statement.setString(8, entity.getPassportNumber());

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DaoException {

    }

    @Override
    public List<Order> getAll() throws DaoException {
        List<Order> orders = new ArrayList<>();

        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS_QUERY)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                int carId = resultSet.getInt(3);
                int statusId = resultSet.getInt(4);
                Date startDate = resultSet.getDate(5);
                Date endDate = resultSet.getDate(6);
                String rejectionComment = resultSet.getString(7);
                String returnComment = resultSet.getString(8);
                String passportNumber = resultSet.getString(9);

                Order order = new Order(id, userId, carId, statusId, startDate, endDate, rejectionComment, returnComment, passportNumber);
                orders.add(order);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }

        return orders;
    }
}
