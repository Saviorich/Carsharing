package by.epam.carsharing.model.dao.impl;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.connection.exception.ConnectionPoolException;
import by.epam.carsharing.model.dao.PassportDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.Passport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class PassportDaoImpl implements PassportDao {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String GET_BY_ID_QUERY = "SELECT * FROM passport WHERE passport_number=?;";
    private static final String ADD_PASSPORT_QUERY = "INSERT INTO passport " +
            "(passport_number, identification_number, issue_date) " +
            "VALUE (?, ?, ?);";

    @Override
    public void add(Passport entity) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_PASSPORT_QUERY)
        ) {
            statement.setString(1, entity.getPassportNumber());
            statement.setString(2, entity.getIdentificationNumber());
            statement.setDate(3, new java.sql.Date(entity.getIssueDate().getTime()));

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Passport> getByPassportNumber(String passportNumber) throws DaoException {
        Optional<Passport> passport = Optional.empty();
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)
        ) {
            statement.setString(1, passportNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String identificationNumber = resultSet.getString(2);
                Date issueDate = resultSet.getDate(3);
                passport = Optional.of(new Passport(passportNumber, identificationNumber, issueDate));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return passport;
    }
}
