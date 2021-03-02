package by.epam.carsharing.dao.impl;

import by.epam.carsharing.connection.ConnectionPool;
import by.epam.carsharing.dao.UserDao;
import by.epam.carsharing.entity.Role;
import by.epam.carsharing.entity.user.User;
import by.epam.carsharing.exception.ConnectionPoolException;
import by.epam.carsharing.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final String LOGIN_QUERY = "SELECT users.id, users.email, users.user_password, role.role FROM users INNER JOIN role on users.role = role.id WHERE email=? AND user_password=?;";
    private static final String REGISTER_QUERY = "INSERT INTO users (email, user_password, role) VALUE (?, ?, ?);";

    private static final int DEFAULT_ROLE_ID = 1;

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void registerUser(String email, String password, int role) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(REGISTER_QUERY);
        ){
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setInt(3, role);

            statement.execute();
        } catch (SQLException | ConnectionPoolException e){
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void registerUser(String email, String password) throws DaoException {
        registerUser(email, password, DEFAULT_ROLE_ID);
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return null;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> user = Optional.empty();
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(LOGIN_QUERY)
        ){
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer id = (Integer) resultSet.getObject(1);
                String name = resultSet.getString(2);
                String mail = resultSet.getString(3);
                Role role = Role.valueOf(resultSet.getString(4).toUpperCase());
                user = Optional.of(new User(id, name, mail, role));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public Optional<User> getById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<User> getAll() throws DaoException {
        return null;
    }

    @Override
    public void save(User entity) throws DaoException {

    }

    @Override
    public void deleteById(int id) throws DaoException {

    }
}


