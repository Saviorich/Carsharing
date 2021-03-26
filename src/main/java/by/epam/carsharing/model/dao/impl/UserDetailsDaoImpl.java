package by.epam.carsharing.model.dao.impl;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.connection.exception.ConnectionPoolException;
import by.epam.carsharing.model.dao.UserDetailsDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDetailsDaoImpl implements UserDetailsDao {

    private static final String ADD_DETAILS_QUERY = "INSERT INTO user_details (" +
            "user_id, passport_number, phone_number, first_name, second_name, middle_name) " +
            "VALUE (?, ?, ?, ?, ?, ?);";
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public Optional<UserDetails> getById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<UserDetails> getAll() throws DaoException {
        return null;
    }

    @Override
    public void add(UserDetails entity) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_DETAILS_QUERY);
        ) {
            statement.setInt(1, entity.getUser().getId());
            statement.setString(2, entity.getPassportNumber());
            statement.setString(3, entity.getPhoneNumber());
            statement.setString(4, entity.getFirstName());
            statement.setString(5, entity.getSecondName());
            statement.setString(6, entity.getMiddleName());

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DaoException {

    }

    @Override
    public void update() throws DaoException {

    }
}
