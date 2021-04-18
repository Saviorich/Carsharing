package by.epam.carsharing.model.dao.impl;

import by.epam.carsharing.model.connection.ConnectionPool;
import by.epam.carsharing.model.dao.UserDao;
import by.epam.carsharing.model.entity.Role;
import by.epam.carsharing.model.entity.user.Passport;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.connection.exception.ConnectionPoolException;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.UserDetail;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String GET_ALL_QUERY = "SELECT users.id, email, user_password, role.role FROM users" +
            " INNER JOIN role on users.role = role.id;";
    private static final String LOGIN_QUERY = "SELECT users.id, users.email, users.user_password, role.role FROM users" +
            " INNER JOIN role on users.role = role.id WHERE email=? AND user_password=?;";
    private static final String GET_BY_ID_QUERY = "SELECT users.id, email, user_password, role.role FROM users" +
            " INNER JOIN role on users.role = role.id WHERE users.id=?;";
    private static final String REGISTER_QUERY = "INSERT INTO users (email, user_password, role) VALUE (?, ?, ?);";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id=?";
    private static final String ADD_PASSPORT_QUERY = "INSERT INTO passport " +
            "(passport_number, identification_number, issue_date) " +
            "VALUE (?, ?, ?);";
    private static final String ADD_DETAILS_QUERY = "INSERT INTO user_details (" +
            "user_id, passport_number, phone_number, first_name, second_name, middle_name) " +
            "VALUE (?, ?, ?, ?, ?, ?);";

    private static final int DEFAULT_ROLE_ID = 1;

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void registerUser(String email, String password, UserDetail details, Passport passport, int role) throws DaoException {
        Connection connection = null;
        try {
            connection = pool.takeConnection();

            PreparedStatement statement = connection.prepareStatement(REGISTER_QUERY, Statement.RETURN_GENERATED_KEYS);

            connection.setAutoCommit(false);

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setInt(3, role);
            statement.executeUpdate();

            logger.log(Level.DEBUG, "User added successfully");

            ResultSet generatedKeys = statement.getGeneratedKeys();

            statement = connection.prepareStatement(ADD_PASSPORT_QUERY);
            statement.setString(1, passport.getPassportNumber());
            statement.setString(2, passport.getIdentificationNumber());
            statement.setDate(3, new Date(passport.getIssueDate().getTime()));
            statement.execute();

            logger.log(Level.DEBUG, "Passport added successfully");

            statement = connection.prepareStatement(ADD_DETAILS_QUERY);
            if (generatedKeys.next()) {
                statement.setInt(1, generatedKeys.getInt(1));
                statement.setString(2, details.getPassportNumber());
                statement.setString(3, details.getPhoneNumber());
                statement.setString(4, details.getFirstName());
                statement.setString(5, details.getSecondName());
                statement.setString(6, details.getMiddleName());
                statement.execute();
            }
            logger.log(Level.DEBUG, "Details added successfully");

            connection.commit();
            statement.close();
        } catch (SQLException | ConnectionPoolException e){
            try {
                connection.rollback();
            } catch (SQLException throwable) {
                throw new DaoException(throwable);
            }
            throw new DaoException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException sqlException) {
                throw new DaoException(sqlException);
            }
        }
    }

    @Override
    public void registerUser(String email, String password, UserDetail details, Passport passport) throws DaoException {
        registerUser(email, password, details, passport, DEFAULT_ROLE_ID);
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(LOGIN_QUERY)
        ){
            statement.setString(1, email);
            statement.setString(2, password);

            return executeForSingleResult(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> getById(int id) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)
        ){
            statement.setInt(1, id);

            return executeForSingleResult(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
        ){
            return executeForManyResults(statement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void add(User entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public List<User> executeForManyResults(PreparedStatement statement) throws SQLException {
        List<User> users = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String email = resultSet.getString(2);
            String password = resultSet.getString(3);
            Role role = Role.valueOf(resultSet.getString(4).toUpperCase());

            users.add(new User(id, email, password, role));
        }
        return users;
    }

    public Optional<User> executeForSingleResult(PreparedStatement statement) throws SQLException {
        Optional<User> user = Optional.empty();

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String email = resultSet.getString(2);
            String password = resultSet.getString(3);
            Role role = Role.valueOf(resultSet.getString(4).toUpperCase());

            user = Optional.of(new User(id, email, password, role));
        }
        return user;
    }
}