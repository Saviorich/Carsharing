package by.epam.carsharing.model.dao.impl;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.connection.exception.ConnectionPoolException;
import by.epam.carsharing.model.dao.PaymentDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String ADD_PAYMENT_QUERY = "INSERT INTO payment (order_id, status_id, total_price, payment_date)" +
            "VALUE (?, (SELECT id FROM status WHERE status_group='payment' AND status_name=?), ?, CURRENT_DATE);";

    @Override
    public Optional<Payment> getById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Payment> getAll() throws DaoException {
        return null;
    }

    @Override
    public void add(Payment entity) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_PAYMENT_QUERY);
        ) {
            statement.setInt(1, entity.getOrderId());
            statement.setString(2, entity.getStatus().toString().toLowerCase());
            statement.setBigDecimal(3, entity.getTotalPrice());

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
    public List<Payment> executeForManyResults(PreparedStatement statement) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Payment> executeForSingleResult(PreparedStatement statement) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
