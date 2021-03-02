package by.epam.carsharing.dao;

import by.epam.carsharing.entity.user.User;
import by.epam.carsharing.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    void registerUser(String email, String password) throws DaoException;
    void registerUser(String email, String passowrd, int role) throws DaoException;

    Optional<User> findUserById(Integer id);
    Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException;
}
