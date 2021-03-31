package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.entity.user.Passport;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.UserDetail;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    void registerUser(String email, String password, UserDetail details, Passport passport) throws DaoException;
    void registerUser(String email, String password, UserDetail details, Passport passport, int role) throws DaoException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException;
}
